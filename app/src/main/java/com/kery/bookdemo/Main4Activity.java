package com.kery.bookdemo;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.kery.bookdemo.img.MultiImageSelectorFragment;
import com.kery.bookdemo.img.PublishStatusActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main4Activity  extends AppCompatActivity implements MultiImageSelectorFragment.Callback {

    // Single choice
    public static final int MODE_SINGLE = 0;
    // Multi choice
    public static final int MODE_MULTI = 1;

    /**
     * Max image size，int，{@link #DEFAULT_IMAGE_SIZE} by default
     */
    public static final String EXTRA_SELECT_COUNT = "max_select_count";
    /**
     * Select mode，{@link #MODE_MULTI} by default
     */
    public static final String EXTRA_SELECT_MODE = "select_count_mode";
    /**
     * Whether show camera，true by default
     */
    public static final String EXTRA_SHOW_CAMERA = "show_camera";
    /**
     * Result data set，ArrayList&lt;String&gt;
     */
    public static final String EXTRA_RESULT = "select_result";

    public static final String FROM_MAIN_ACTIVITY = "FROM_MAIN_ACTIVITY";
    /**
     * Original data set
     */
    public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_list";
    // Default image size
    private static final int DEFAULT_IMAGE_SIZE = 9;

    private ArrayList<String> selectedPictures = new ArrayList<>();
    private Button mSubmitButton;
    private int mDefaultCount = DEFAULT_IMAGE_SIZE;
    private Boolean mIsFromMainPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MIS_NO_ACTIONBAR);
        setContentView(R.layout.mis_activity_default);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final Intent intent = getIntent();
        mIsFromMainPage = intent.getBooleanExtra("isfirstin", false);
        mDefaultCount = intent.getIntExtra(EXTRA_SELECT_COUNT, DEFAULT_IMAGE_SIZE);
        final int mode = intent.getIntExtra(EXTRA_SELECT_MODE, MODE_MULTI);
        final boolean isShow = intent.getBooleanExtra(EXTRA_SHOW_CAMERA, true);
        if (mode == MODE_MULTI && intent.hasExtra(EXTRA_DEFAULT_SELECTED_LIST)) {
            selectedPictures = intent.getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST);
        }

        mSubmitButton = (Button)findViewById(R.id.commit);
        if (mode == MODE_MULTI) {

            updateDoneText(selectedPictures);

            mSubmitButton.setVisibility(View.VISIBLE);
            mSubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsFromMainPage) {
                        Intent data = new Intent(Main4Activity.this, PublishStatusActivity.class);
                        data.putStringArrayListExtra(FROM_MAIN_ACTIVITY, selectedPictures);
                        data.putExtra(PublishStatusActivity.KEY_TYPE, PublishStatusActivity.TYPE_PICTURE);
                        startActivity(data);
                    } else {
                        if (selectedPictures != null && selectedPictures.size() > 0) {
                            Intent data = new Intent();
                            data.putStringArrayListExtra(EXTRA_RESULT, selectedPictures);
                            setResult(RESULT_OK, data);
                        } else {
                            setResult(RESULT_CANCELED);
                        }
                    }
                    finish();
                }
            });

        } else {
            mSubmitButton.setVisibility(View.GONE);
        }

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putInt(MultiImageSelectorFragment.EXTRA_SELECT_COUNT, mDefaultCount);
            bundle.putInt(MultiImageSelectorFragment.EXTRA_SELECT_MODE, mode);
            bundle.putBoolean(MultiImageSelectorFragment.EXTRA_SHOW_CAMERA, isShow);
            bundle.putStringArrayList(MultiImageSelectorFragment.EXTRA_DEFAULT_SELECTED_LIST, selectedPictures);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.image_grid, Fragment.instantiate(this, MultiImageSelectorFragment.class.getName(), bundle))
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Update done button by select image data
     *
     * @param resultList selected image data
     */
    private void updateDoneText(ArrayList<String> resultList) {
        int size = 0;
        if (resultList == null || resultList.size() <= 0) {
            mSubmitButton.setText(R.string.mis_action_done);
            mSubmitButton.setEnabled(false);
        } else {
            size = resultList.size();
            mSubmitButton.setEnabled(true);
        }
        mSubmitButton.setText(getString(R.string.mis_action_button_string,
                getString(R.string.mis_action_done), size, mDefaultCount));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
        }
    }

    @Override
    public void onSingleImageSelected(String path) {
        Intent data = new Intent();
        selectedPictures.add(path);
        data.putStringArrayListExtra(EXTRA_RESULT, selectedPictures);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onImageSelected(String path) {
        if (!selectedPictures.contains(path)) {
            selectedPictures.add(path);
        }
        updateDoneText(selectedPictures);
    }

    @Override
    public void onImageUnselected(String path) {
        if (selectedPictures.contains(path)) {
            selectedPictures.remove(path);
        }
        updateDoneText(selectedPictures);
    }

    @Override
    public void onCameraShot(File imageFile) {
        if (imageFile != null) {
            // notify system the image has change
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(imageFile)));
            if (mIsFromMainPage) {
                Intent data = new Intent(Main4Activity.this, PublishStatusActivity.class);
                selectedPictures.add(imageFile.getAbsolutePath());
                data.putStringArrayListExtra(FROM_MAIN_ACTIVITY, selectedPictures);
                data.putExtra("isfirstin", true);
                data.putExtra("type_key", PublishStatusActivity.TYPE_PICTURE);
                startActivity(data);
            } else {
                Intent data = new Intent();
                selectedPictures.add(imageFile.getAbsolutePath());
                data.putStringArrayListExtra(EXTRA_RESULT, selectedPictures);
                setResult(RESULT_OK, data);
            }

            finish();
        }
    }
}
