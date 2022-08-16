package com.porotov.articleservice.DTO.articleDTO;

public enum ArticleStatusDTO {

    CREATED ("created"),
    ALREADY_EXIST ("already exist");

    private String status;

    ArticleStatusDTO(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "status - " + status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
