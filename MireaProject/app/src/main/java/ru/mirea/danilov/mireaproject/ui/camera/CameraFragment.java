package ru.mirea.danilov.mireaproject.ui.camera;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.mirea.danilov.mireaproject.MainActivity;
import ru.mirea.danilov.mireaproject.R;
import ru.mirea.danilov.mireaproject.databinding.FragmentCameraBinding;

public class CameraFragment extends Fragment {
    private FragmentCameraBinding binding;
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 100;
    private ImageView imageView;
    public static final int CAMERA_REQUEST = 0;
    private boolean isWork = false;
    private Uri imageUri;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        imageView = binding.imageView;
        MainActivity activity = (MainActivity) getActivity();
        // Выполняется проверка на наличие разрешений на использование камеры и запись впамять
        int cameraPermissionStatus =
                ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        int storagePermissionStatus = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED && storagePermissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            // Выполняется запрос к пользователь на получение необходимых разрешений
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_CAMERA);
        }

        binding.snapButton.setOnClickListener(this::imageViewOnClick);

        return root;
    }

    public void imageViewOnClick(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // проверка на наличие разрешений для камеры
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null && isWork)
        {
            File photoFile;
            try {
                photoFile = createImageFile();
                // генерирование пути к файлу на основе authorities
                String authorities = getActivity().getApplicationContext().getPackageName() + ".fileprovider";
                imageUri = FileProvider.getUriForFile(getActivity(), authorities, photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CameraFragment.CAMERA_REQUEST && resultCode == RESULT_OK) {
            imageView.setImageURI(imageUri);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}