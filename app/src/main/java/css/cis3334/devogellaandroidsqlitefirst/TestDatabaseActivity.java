package css.cis3334.devogellaandroidsqlitefirst;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

// Tests the database. Uses a ListActivity to display data
public class TestDatabaseActivity extends ListActivity {
    private CommentsDataSource datasource;

    EditText etRating;

    // Creates/opens a comments data source, a list of the comments in that data source, and uses the SimpleCursorAdapter to show the elements in a list view
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        datasource = new CommentsDataSource(this);
        datasource.open();

        List<Comment> values = datasource.getAllComments();

        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Performs an action based upon which button was clicked
    // Add: creates a new comment using a random string value from the comments array
    // Delete: removes the first comment in the array
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment = null;
        switch (view.getId()) {
            case R.id.add:
                etRating = (EditText) findViewById(R.id.editTextRating);
                String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                comment = datasource.createComment(comments[nextInt], etRating.getText().toString());
                adapter.add(comment);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    comment = (Comment) getListAdapter().getItem(0);
                    datasource.deleteComment(comment);
                    adapter.remove(comment);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    // Reopens the data source if the session is unpaused
    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    // Closes the data source if the session is paused
    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}