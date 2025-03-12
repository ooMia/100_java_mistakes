package com.example.mistakes.api.hello;

import com.example.mistakes.base.MessageDTO;

public record HelloDTO(String message) implements MessageDTO {
}