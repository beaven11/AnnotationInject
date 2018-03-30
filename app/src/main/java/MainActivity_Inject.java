import com.beaven.testapp.MainActivity;
import com.beaven.testapp.R;

/**
 * 创建时间: 2018/03/30 17:05<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/30 17:05<br>
 * 描述:
 */
public class MainActivity_Inject {

    public MainActivity_Inject(MainActivity mainActivity) {
        mainActivity.setContentView(R.layout.activity_main);
    }
}
