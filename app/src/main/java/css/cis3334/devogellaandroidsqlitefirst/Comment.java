package css.cis3334.devogellaandroidsqlitefirst;

// Database model; contains the data to be saved in the database and shows the user interface.
public class Comment {
    private long id;            // "Current ID" long value taken from a database entry
    private String comment;     // "Current Comment" string value taken from the database
    private String rating;

    // Returns the long value of the most recently set "Current ID".
    public long getId() {
        return id;
    }

    // Sets the "Current ID" to the specified long value.
    public void setId(long id) {
        this.id = id;
    }

    // Returns the text of the most recently set "Current Comment".
    public String getComment() {
        return comment;
    }

    // Sets the "Current Comment" to the specified string value.
    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getRating() { return rating; }


    public void setRating(String rating) { this.rating = rating; }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() { return "[" + rating + "]: " + comment; }
}