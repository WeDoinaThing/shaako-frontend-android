package com.github.meafs.recover.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContentModel implements Serializable {

    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("pk")
    @Expose
    private Integer pk;
    @SerializedName("fields")
    @Expose
    private Fields fields;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }


    public class Fields implements Serializable{

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("details")
        @Expose
        private String details;
        @SerializedName("tags")
        @Expose
        private String tags;
        @SerializedName("associated_link")
        @Expose
        private String associatedLink;
        @SerializedName("added_by")
        @Expose
        private Integer addedBy;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("status")
        @Expose
        private Boolean status;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getAssociatedLink() {
            return associatedLink;
        }

        public void setAssociatedLink(String associatedLink) {
            this.associatedLink = associatedLink;
        }

        public Integer getAddedBy() {
            return addedBy;
        }

        public void setAddedBy(Integer addedBy) {
            this.addedBy = addedBy;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

    }
}
