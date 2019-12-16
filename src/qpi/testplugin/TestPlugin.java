package qpi.testplugin;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import ru.nukkitx.forms.Form;
import ru.nukkitx.forms.elements.CustomForm;
import ru.nukkitx.forms.elements.ImageType;
import ru.nukkitx.forms.elements.ModalForm;
import ru.nukkitx.forms.elements.SimpleForm;

import java.util.ArrayList;
import java.util.List;

public class TestPlugin extends PluginBase{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            Player p = (Player) sender;
            Form form;

            switch (command.getName().toLowerCase()){
                case "simpleform":
                    form = new SimpleForm("Тестовая форма");

                    ((SimpleForm)form).setContent("Тут должен быть написан какой-то тестовый текст.")
                            .addButton("Кнопа")
                            .addButton("Кнопка с картинкой", ImageType.PAHT, "textures/items/diamond")
                            .send(p, (player, formWindow, o, hashMap) -> {
                                if(o == null) return;

                                p.sendMessage(o.toString());
                                getLogger().info(o.toString());
                            });
                    break;

                case "customform":
                    form = new CustomForm();
                    List<String> list = new ArrayList<>();
                    list.add("1 пункт");
                    list.add("2 пункт");
                    list.add("3 пункт");
                    list.add("4 пункт");
                    list.add("5 пункт");

                    ((CustomForm)form).addLabel("Тут должен быть написан какой-то рандомный текст, но написано это.")
                            .addDropDown("Выпадающий список", list)
                            .addInput("Текстовое поле")
                            .addSlider("Ползунок", 1, 100)
                            .addStepSlider("Пошаговый ползунок", list)
                            .addToggle("Переключатель")
                            .send(p, (player, formWindow, o, hashMap) -> {
                                if(o == null) return;

                                p.sendMessage(o.toString());
                                getLogger().info(o.toString());
                            });
                    break;

                case "modalform":
                    form = new ModalForm("Заголовок", "Текст", "Кнопка 1", "Кнопка 2");

                    form.send(p, (player, formWindow, o, hashMap) -> {
                                if(o == null) return;

                                p.sendMessage(o.toString());
                                getLogger().info(o.toString());
                            });
                    break;
            }
        }else sender.sendMessage("Данную команду можно вводить только в игре!");

        return true;
    }
}
