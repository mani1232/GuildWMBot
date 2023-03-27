package ua.mani123.localization.languages;

import net.dv8tion.jda.api.EmbedBuilder;
import ua.mani123.localization.DefaultLang;

import java.awt.*;

public class RULang extends DefaultLang {

    public RULang() {
        embed_choose_lang = new EmbedBuilder().setTitle("Выберите язык").setDescription("Выберите язык, который вы хотите использовать для своей учетной записи.").setColor(Color.ORANGE);
        embed_default_success_title = new EmbedBuilder().setTitle("Успешно выполнено").setColor(Color.GREEN);
        embed_banned = new EmbedBuilder().setTitle("Вы были забанены").setDescription("Это означает что у вас больше нет доступа к боту.").setColor(Color.RED);
        embed_description_account_info = """
                            
                ```Markdown
                Имя аккаунта:\s
                > %s
                Баланс:\s
                > %s
                Игровой баланс:\s
                > %s
                Опыт:\s
                > %s
                Язык:\s
                > %s
                ```
                ||`UUID ключ: %s`||
                            
                """;
        embed_default_error_title = new EmbedBuilder().setTitle("В вашем действии есть ошибки").setColor(Color.RED);
        not_set = "Не задано";
        embed_not_working = new EmbedBuilder().setTitle("Временная ошибка").setDescription("Это означает что эта функция в данный момент не работает или выключена.").setColor(Color.RED);
        embed_deposit = """
                            
                ```Markdown
                Пополнение на:\s
                > %s
                Реальный баланс до:\s
                > %s
                Реальный баланс после:\s
                > %s
                ```
                Выберите в меню метод оплаты
                """;
        embed_title_buy_product_type = "Выберите тип товара";
        embed_description_buy_product_type = "После выбора типа товара бот отправит меню для выбора продолжительности или вариации товара.";
        embed_buy_variation = """
                            
                ```Markdown
                Товар:\s
                > %s
                ```
                Выберите вариацию продукта
                """;
        embed_buy = """
                            
                ```Markdown
                Цена:\s
                > %s
                Статус:\s
                > %s
                ```
                """;
        month = "месяц";
        months = "месяцев";
        cancel = "Отмена";
        embed_balance_not_enough = new EmbedBuilder().setTitle("Недостаточно денег").setDescription("Вы можете пополнить свой баланс с помощью команды `/balance deposit`").setColor(Color.RED);
        embed_need_online = new EmbedBuilder().setTitle("Инфо ошибка").setDescription("Вы должны быть онлайн, чтобы бот мог выполнить эту команду").setColor(Color.RED);
        confirm = "Подтвердить";
        waiting_confirmation = "Ожидание подтверждения";
        canceled = "Отменено";
        accepted_wait = "Принят, ожидание получения";
        send_money = "Вы получили %s от %s";
        modal_buy_info_title = "Заказ";
        private_account = "Этот аккаунт приватный";
    }
}
