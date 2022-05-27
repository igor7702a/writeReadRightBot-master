package ru.taksebe.telegram.writeRead.telegram;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
@Tag(name="Название контроллера", description="Контроллер доступа к телеграм")
public class WebhookController {
    private final WriteReadBot writeReadBot;

    @Operation(summary = "Основной метод контроллера", description = "Входная точка для телеграм")
    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        BotApiMethod<?> myResBAM = writeReadBot.onWebhookUpdateReceived(update);
        return myResBAM;
    }
}