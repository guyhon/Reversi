public enum palcement {

    GARY("#A9A9A9"),
    BLUE("#87CEFA"),
    RED("#F08080"),

    GLOW("#FFFACD");

    private final String color;

    palcement(String color){
        this.color = color;

    }

    @Override
    public String toString() {
        return color;
    }


    public String getColor() {
        return color;
    }


    public boolean equals(palcement p){
        if(this.getColor()==p.getColor())
           return true;
        else
            return false;
    }



}
