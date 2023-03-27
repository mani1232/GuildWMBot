package ua.mani123.command.commands;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import ua.mani123.Main;
import ua.mani123.command.WMCommand;
import ua.mani123.database.DatabaseObjects.WMCategory;
import ua.mani123.database.DatabaseObjects.WMUser;

import java.awt.*;
import java.util.List;
import java.util.Objects;

@Getter
public class CategoryCommand extends WMCommand {

    public CategoryCommand(String name) {
        this.name = name;
        this.slashCommandData = Commands.slash(this.name, "Category settings").addSubcommands(
                new SubcommandData("edit", "Send menu for select category")
                        .addOption(OptionType.BOOLEAN, "as-editor", "if false you see only your own categories", true)
                        .addOption(OptionType.STRING, "category-id", "select category by id", false)
                , new SubcommandData("create", "creating new category")

        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event, WMUser wmUser) {
        switch (Objects.requireNonNull(event.getSubcommandName())) {
            case "edit" -> {
                try {
                    long categoryId = event.getOption("category-id").getAsLong();
                    WMCategory wmCategory = Main.dataBase.getCategoryByDiscordId(categoryId);
                    if (wmCategory != null) {

                    } else {

                    }
                } catch (Exception ignore) {
                    if (event.getOption("as-editor").getAsBoolean()) {
                        List<WMCategory> categories = Main.dataBase.getCategoriesByEditorId(wmUser.getId());
                    } else {
                        List<WMCategory> categories = Main.dataBase.getCategoriesByOwnerId(wmUser.getId());

                    }
                }
            }
            case "create" -> {
                if (wmUser.getCategoriesOwner().size() <= wmUser.getMaxCategories()) {
                    WMCategory wmCategory = new WMCategory();
                    event.getGuild().createCategory("test-" + event.getUser().getName()).queue(category -> wmCategory.setCategoryId(category.getIdLong()));
                    wmUser.getCategoriesOwner().add(wmCategory.getId());
                    Main.dataBase.insertCategory(wmCategory);
                    Main.dataBase.insertUser(wmUser);
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
                } else {
                    event.replyEmbeds(new EmbedBuilder().setTitle(String.format(wmUser.getLang().getStrings().max_error, wmUser.getCategoriesOwner().size(), wmUser.getMaxCategories())).setColor(Color.RED).build()).setEphemeral(true).queue();
                }
            }
        }
    }
}
