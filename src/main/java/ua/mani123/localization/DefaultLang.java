package ua.mani123.localization;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class DefaultLang {
     public EmbedBuilder embed_choose_lang = new EmbedBuilder().setTitle("Select language").setDescription("Select the language you want to use for your account").setColor(Color.ORANGE);
     public EmbedBuilder embed_default_success_title = new EmbedBuilder().setTitle("Successfully completed").setColor(Color.GREEN);
     public EmbedBuilder embed_default_error_title = new EmbedBuilder().setTitle("Your action has errors").setColor(Color.RED);
     public EmbedBuilder embed_banned = new EmbedBuilder().setTitle("You have been banned").setDescription("This means that you no longer have access to the bot.").setColor(Color.RED);
     public EmbedBuilder embed_not_working = new EmbedBuilder().setTitle("Timed error").setDescription("This means that this function is currently not working or disabled.").setColor(Color.RED);
     public EmbedBuilder embed_balance_not_enough = new EmbedBuilder().setTitle("Not enough money").setDescription("You can replenish your balance using the command `/balance deposit`").setColor(Color.RED);
     public EmbedBuilder embed_need_online = new EmbedBuilder().setTitle("Info error").setDescription("You must be online for the bot to execute this command").setColor(Color.RED);
     public String embed_default_footer = "by mani123";
     public String embed_description_account_info = """
                        
            ```Markdown
            Real name:\s
            > %s
            Balance:\s
            > %s
            Game balance:\s
            > %s
            Experience:\s
            > %s
            Language:\s
            > %s
            ```
            ||`UUID key: %s`||
                        
            """;

     public String embed_deposit = """
                        
            ```Markdown
            Replenishment for:\s
            > %s
            Balance before:\s
            > %s
            Balance after:\s
            > %s
            ```
            Choose a payment method from the menu
            """;

     public String embed_buy_variation = """
                        
            ```Markdown
            Product:\s
            > %s
            ```
            Choose a product variant
            """;

     public String embed_buy = """
                        
            ```Markdown
            Price:\s
            > %s
            Status:\s
            > %s
            ```
            """;

     public String embed_title_buy_product_type = "Select product type";
     public String month = "month";
     public String send_money = "You received %s from %s";
     public String confirm = "Confirm";
     public String cancel = "Cancel";
     public String months = "months";
     public String waiting_confirmation = "Waiting for confirmation";
     public String canceled = "Canceled";
     public String embed_description_buy_product_type = "After selecting the type of product, the bot will send a menu to select the duration or variation of the product";
     public String not_set = "Not set";
     public String accepted_wait = "Accepted, waiting to receive";
     public String private_account = "This account is private";
     public String modal_buy_info_title = "Order";
}
