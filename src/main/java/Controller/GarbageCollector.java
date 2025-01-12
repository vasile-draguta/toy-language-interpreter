package Controller;

import Exceptions.GarbageCollectorException;
import Exceptions.HeapException;
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

    public static void garbageCollector(List<ProgState> states) {
        if (states.isEmpty()) {
            return;
        }

        List<Integer> reachableAddresses = states.stream().flatMap(state -> getAddressesFromSymTable(state).stream()).toList();
        IHeapTable heapTable = states.get(0).getHeapTable();
        List<Integer> addressesList = heapTable.toMap().keySet().stream().filter(e -> !reachableAddresses.contains(e)).toList();

        addressesList.forEach(e -> {
            try {
                heapTable.deallocate(e);
            } catch (HeapException ex) {
                throw new GarbageCollectorException("Garbage Collector failed to deallocate address " + e);
            }
        });
    }
}