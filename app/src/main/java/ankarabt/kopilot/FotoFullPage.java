package ankarabt.kopilot;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class FotoFullPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_full_page);

        String title = getIntent().getStringExtra("title");
        Bitmap bitmap = getIntent().getParcelableExtra("image");

        TextView titleTextView = (TextView) findViewById(R.id.title_full_page);
        titleTextView.setText(title);

        ImageView imageView = (ImageView) findViewById(R.id.image_full_page);
        imageView.setImageBitmap(bitmap);
    }
}
