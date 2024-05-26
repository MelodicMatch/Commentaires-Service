package model;

public enum Sorting {
    DATEASC,
    DATEDESC,
    RATINGASC,
    RATINGDESC;

    public static Sorting fromString(String string) {
        return switch (string) {
            case "dtasc" -> DATEASC;
            case "dtdsc" -> DATEDESC;
            case "rtgasc" -> RATINGASC;
            case "rtgdsc" -> RATINGDESC;
            default -> DATEASC;
        };
    }

    public String toSQL() {
        switch (this.ordinal()) {
            case 0:
                return "ORDER BY date ASC ";
            case 1:
                return "ORDER BY date DESC ";
            case 2:
                return "ORDER BY rating ASC ";
            case 3:
                return "ORDER BY rating DESC ";
            default:
                return null;
        }
    }
}
