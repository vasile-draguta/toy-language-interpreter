package Controller;

import Exceptions.GarbageCollectorException;
import Model.States.HeapTable.IHeapTable;
import Model.States.ProgState;
import Model.Values.IValue;
import Model.Values.RefValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GarbageCollector {
    public static List<Integer> getAddressesFromSymTable(ProgState state) {
        return state.getSymTable().toMap().values().stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> (RefValue) value)
                .flatMap(value -> {
                    List<Integer> addresses = new ArrayList<>();
                    while (true) {
                        if (value.getAddress() == 0) {
                            break;
                        }
                        addresses.add(value.getAddress());
                        IValue nextValue;
                        try {
                            nextValue = state.getHeapTable().read(value.getAddress());
                        } catch (Exception e) {
                            break;
                        }

                        if (!(nextValue instanceof RefValue)) {
                            break;
                        }
                        value = (RefValue) nextValue;
                    }
                    return addresses.stream();
                }).collect(Collectors.toList());
    }

    public static void garbageCollector(ProgState state) {
        List<Integer> activeAddresses = GarbageCollector.getAddressesFromSymTable(state);

        IHeapTable heapTable = state.getHeapTable();
        List<Integer> addresses = heapTable.toMap().keySet().stream().filter(e -> !activeAddresses.contains(e)).toList();

        addresses.forEach(e -> {
            try {
                heapTable.deallocate(e);
            } catch (GarbageCollectorException garbageCollectorException) {
                throw new GarbageCollectorException("Garbage Collector failed to deallocate address " + e);
            }
        });


    }
}