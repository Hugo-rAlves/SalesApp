package com.hugoalves.sales.model.dto;

import com.hugoalves.sales.model.enumeration.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
