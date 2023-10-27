package com.deloitte.sprintbatch.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "incidents")
public class Incident {

    @Field("Incident Number")
    private String incidentNumber;
    @Field("Highest Offense Description")
    private String highestOffenseDescription;
    @Field("Highest Offense Code")
    private String highestOffenseCode;
    @Field("Family Violence")
    private String familyViolence;
    @Field("Occurred Date Time")
    private String occurredDateTime;
    @Field("Occurred Date")
    private String occurredDate;
    @Field("Occurred Time")
    private String occurredTime;
    @Field("Report Date Time")
    private String reportDateTime;
    @Field("Report Date")
    private String reportDate;
    @Field("Report Time")
    private String reportTime;
    @Field("Location Type")
    private String locationType;
    @Field("Address")
    private String address;
    @Field("Zip Code")
    private String zipCode;
    @Field("Council District")
    private String councilDistrict;
    @Field("APD Sector")
    private String apdSector;
    @Field("APD District")
    private String apdDistrict;
    @Field("PRA")
    private String pra;
    @Field("Census Tract")
    private String censusTract;
    @Field("Clearance Status")
    private String clearanceStatus;
    @Field("Clearance Date")
    private String clearanceDate;
    @Field("UCR Category")
    private String ucrCategory;
    @Field("Category Description")
    private String categoryDescription;
    @Field("X-coordinate")
    private String xCoordinate;
    @Field("Y-coordinate")
    private String yCoordinate;
    @Field("Latitude")
    private String latitude;
    @Field("Longitude")
    private String longitude;
    @Field("Location")
    private String location;

}
