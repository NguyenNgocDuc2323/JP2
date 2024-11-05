package Entity;

public enum RoomType {
    Single("Single"),Double("Double"),Queen("Queen"),Quad("Quad"),Triple("Triple");
    private String type;
    RoomType(String type) {
        this.type = type;
    }
}
