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
            Display name:\s
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

     public String rules = """
             ```Markdown
             
             > General provisions
             1. Members of the server Discord are equal before the rules regardless of experience and role.
             2. Swearing is allowed, but without abuse.
             3. Insulting other users is prohibited.
             4. NSFW: shock content, pornography and politics are not allowed. (except special channel)
             5. Abuse of Caps Lock is forbidden.
             6. All types of flooding are forbidden.
             7. Hard trolling is forbidden.
             
             > Placing links
             1. It is forbidden to advertise without the consent of the administrator.
             2. Spamming in personal SMS with other users is not allowed.
             3. It's not allowed to throw links with domains on YouTube, VK, Roblox and Wiki. Placing links by agreement with the administrator.
             
             > Nicknames and avatars
             The administrator has the right to request a change of nickname and picture if he or she thinks they offend someone.
             2. Nicknames like User, Discord User, NickName and others, including Admin, Moderator, etc. are prohibited.
             3. Usage of names with swear words, insults, religious names, advertising, alcohol/drugs promotion is forbidden.
             4. It is not allowed to use symbols of terrorists and banned organizations, call for violence and extremism.
             5. Nonsensical set of symbols with repetition of one or more letters should not be used.
             6. Pictures with profanity, insults and other forbidden things mentioned above are not allowed.
             
             > Discord rules for the use of channels and subchannels
             1. The channel name is subject to the same requirements as for the Discord server.
             2. In any channel/subchannel, it is prohibited to publish links to donation sites, payment acceptance platforms, sponsorship, donations and other services.
             
             > Responsibility
             1. In case of violation of the rules of the Discord server, measures are taken against users, up to restricting access.
             2. Bypassing the ban by logging in under a different ID or in other ways - ban.
             3. The administrator of the DS has the right to deny access to any participant. He is not required to give reasons or warn about it.
             4. Violation of the norms mentioned above - ban or mute.
             5. Disrespectful attitude towards other users and insult - ban or mute.
             6. Inciting ethnic hatred, conflicts on political and religious grounds - ban or mute.
             7. Broadcasting prohibited streams - ban.
             
             ```
             """;
     public String rules_title = "Rules";
     public String rules_footer = "Ignorance of the rules does not exempt from responsibility";
     public String max_error = "Your account has reached the limit of %s out of %s";
}
