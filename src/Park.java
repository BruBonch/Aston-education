public class Park {
    private int attractionCount = 0;

    public class Attraction {
        private String title;
        private String openingHours;
        private String ticketPrice;

        public Attraction(String title, String openingHours, String ticketPrice) {
            attractionCount++;

            this.title = title;
            this.openingHours = openingHours;
            this.ticketPrice = ticketPrice;
        }

        @Override
        public String toString() {
            return "Attraction {" +
                    "title = '" + title + '\'' +
                    ", openingHours = '" + openingHours + '\'' +
                    ", ticketPrice = '" + ticketPrice + '\'' +
                    '}';
        }
    }

    public int getAttractionCount() {
        return attractionCount;
    }
}
