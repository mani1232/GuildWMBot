package ua.mani123.localization.languages;


import net.dv8tion.jda.api.EmbedBuilder;
import ua.mani123.localization.DefaultLang;

import java.awt.*;

public class UALang extends DefaultLang {

    public UALang() {
        embed_choose_lang = new EmbedBuilder().setTitle("Виберіть мову").setDescription("Виберіть мову, яку потрібно використовувати для вашого облікового запису.").setColor(Color.ORANGE);
        embed_default_success_title = new EmbedBuilder().setTitle("Успішно виконано").setColor(Color.GREEN);
        embed_banned = new EmbedBuilder().setTitle("Ви були забанені").setDescription("Це означає, що у вас більше немає доступу до боту.").setColor(Color.RED);
        embed_description_account_info = """
                            
                ```Markdown
                Реальне ім'я:\s
                > %s
                Баланс:\s
                > %s
                Ігровий баланс:\s
                > %s
                Досвід:\s
                > %s
                Мова:\s
                > %s
                ```
                ||`UUID ключ: %s`||
                            
                """;
        embed_default_error_title = new EmbedBuilder().setTitle("У вашій дії є помилки").setColor(Color.RED);
        not_set = "Не встановлено";
        embed_not_working = new EmbedBuilder().setTitle("Тимчасова помилка").setDescription("Це означає, що ця функція в даний момент не працює або вимкнена.").setColor(Color.RED);
        embed_deposit = """
                            
                ```Markdown
                Поповнення на:\s
                > %s
                Реальний баланс до:\s
                > %s
                Реальний баланс після:\s
                > %s
                ```
                Виберіть спосіб оплати з меню
                """;
        embed_title_buy_product_type = "Виберіть тип товару";
        embed_description_buy_product_type = "Після вибору типу товару бот відправить меню для вибору тривалості чи варіації товару.";
        embed_buy_variation = """
                            
                ```Markdown
                Товар:\s
                > %s
                ```
                Виберіть варіацію продукту
                """;
        embed_buy = """
                            
                ```Markdown
                Ціна:\s
                > %s
                Статус:\s
                > %s
                ```
                """;
        month = "місяць";
        months = "місяців";
        cancel = "Відміна";
        canceled = "Скасовано";
        embed_balance_not_enough = new EmbedBuilder().setTitle("Недостатньо коштів").setDescription("Ви можете поповнити свій баланс за допомогою команди `/balance deposit`").setColor(Color.RED);
        embed_need_online = new EmbedBuilder().setTitle("Инфо помилка").setDescription("Ви повинні бути онлайн, щоб бот міг виконати цю команду").setColor(Color.RED);
        confirm = "Підтвердити";
        waiting_confirmation = "Очікування підтвердження";
        accepted_wait = "Прийнятий, очікування отримання";
        send_money = "Ви отримали %s від %s";
        modal_buy_info_title = "Замовлення";
        private_account = "Цей обліковий запис приватний";
    }
}
