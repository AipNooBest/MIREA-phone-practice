package ru.mirea.danilov.mireaproject.ui.stories;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import ru.mirea.danilov.mireaproject.databinding.FragmentStoriesBinding;

public class StoriesFragment extends Fragment {
    ArrayList<Story> stories = new ArrayList<>();
    private FragmentStoriesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setInitialStories();
        binding = FragmentStoriesBinding.inflate(inflater, container, false);
        StoriesAdapter adapter = new StoriesAdapter(getActivity(), stories);
        binding.recycler.setAdapter(adapter);

        binding.addStoryButton.setOnClickListener(this::onClickAddStory);
        View root = binding.getRoot();
        return root;
    }

    private void setInitialStories(){
        stories.add(new Story("Корованы", "Здраствуйте. Я, Кирилл. Хотел бы чтобы вы сделали игру, 3Д-экшон суть такова… Пользователь может играть лесными эльфами, охраной дворца и злодеем. И если пользователь играет эльфами то эльфы в лесу, домики деревяные набигают солдаты дворца и злодеи. Можно грабить корованы… И эльфу раз лесные то сделать так что там густой лес… А движок можно поставить так что вдали деревья картинкой, когда подходиш они преобразовываются в 3-хмерные деревья"));
        stories.add(new Story("Корованы v2.0", "Здраствуйте. Я, Кирилл. Хотел бы чтобы вы сделали игру, 3Д-экшон суть такова… Пользователь может играть лесными эльфами, охраной дворца и злодеем. И если пользователь играет эльфами то эльфы в лесу, домики деревяные набигают солдаты дворца и злодеи. Можно грабить корованы… И эльфу раз лесные то сделать так что там густой лес… А движок можно поставить так что вдали деревья картинкой, когда подходиш они преобразовываются в 3-хмерные деревья"));
    }

    private void onClickAddStory(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final EditText storyTitle = new EditText(getContext());
        storyTitle.setSingleLine(true);
        alert.setTitle("Создание истории");
        alert.setMessage("Введите название истории");

        alert.setView(storyTitle);

        alert.setPositiveButton("Далее", (dialogInterface, i) -> {
            String titleValue = storyTitle.getText().toString();
            acceptStoryContent(titleValue);
        });

        alert.setNegativeButton("Отмена", (dialogInterface, i) -> {});

        alert.show();
    }

    private void acceptStoryContent(String storyTitle){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final EditText storyContent = new EditText(getContext());
        alert.setTitle("Создание истории");
        alert.setMessage("Введите содержание истории");
        alert.setView(storyContent);

        alert.setPositiveButton("Создать", (dialogInterface, i) -> {
            String storyValue = storyContent.getText().toString();
            stories.add(new Story(storyTitle, storyValue));
        });

        alert.setNegativeButton("Отмена", (dialogInterface, i) -> {});
        alert.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}