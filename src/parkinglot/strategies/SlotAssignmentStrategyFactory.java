package parkinglot.strategies;

import parkinglot.models.SlotAllotmentStrategyType;

public class SlotAssignmentStrategyFactory {
    public static SlotAssignmentStrategy getSlotForType(
            SlotAllotmentStrategyType slotAllotmentStrategyType
            ) {
        return new RandomSlotAssignmentStrategy();
    }
}
