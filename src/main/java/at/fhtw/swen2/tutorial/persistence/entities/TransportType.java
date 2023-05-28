package at.fhtw.swen2.tutorial.persistence.entities;

public enum TransportType {
    CAR, TRAIN, PLANE, BIKE, WALK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

