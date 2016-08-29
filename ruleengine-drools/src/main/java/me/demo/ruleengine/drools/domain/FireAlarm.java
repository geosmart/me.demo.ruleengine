package me.demo.ruleengine.drools.domain;

/**
 * Created by Think on 2016/8/24.
 */
public class FireAlarm {
    public static class Room {
        private String name;

        public Room(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class Sprinkler {
        private Room room;

        private boolean on;

        public Sprinkler(Room room) {
            this.room = room;
            this.on=false;
        }

        public Room getRoom() {
            return room;
        }

        public void setRoom(Room room) {
            this.room = room;
        }

        public boolean isOn() {
            return on;
        }

        public void setOn(boolean on) {
            this.on = on;
        }

        @Override
        public String toString() {
            return "Sprinkler{" +
                    "room=" + room +
                    ", on=" + on +
                    '}';
        }
    }

    public static class Fire {

        private Room room;

        public Fire(Room room) {
            this.room = room;
        }

        public Room getRoom() {
            return room;
        }

        public void setRoom(Room room) {
            this.room = room;
        }

        @Override
        public String toString() {
            return "Fire{" +
                    "room=" + room +
                    '}';
        }
    }

    public static class Alarm {

    }

}
