package com.csye6225.Service;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.datamodel.Announcements;
import com.csye6225.datamodel.DynamoDbConnector;

import java.util.HashMap;
import java.util.List;
/**
 * @author Rexus
 * @date 11/12/19
 */

public class AnnouncementsService {

    static DynamoDbConnector dynamoDb;
    DynamoDBMapper mapper;

    public AnnouncementsService(){
        dynamoDb = new DynamoDbConnector();
        dynamoDb.init();
        mapper = new DynamoDBMapper(dynamoDb.getClient());
    }

    public Announcements getAnnouncements(String boardId, String announcementId) {
        HashMap<String, AttributeValue> query = new HashMap<>();
        query.put(":attribute1", new AttributeValue().withS(boardId));
        query.put(":attribute2", new AttributeValue().withS(announcementId));
        DynamoDBQueryExpression<Announcements> queryExpression = new DynamoDBQueryExpression<Announcements>()
                .withIndexName("boardId-announcementId-index")
                .withConsistentRead(false)
                .withKeyConditionExpression("boardId = :attribute1 and begins_with(announcementId, :attribute2)")
                .withExpressionAttributeValues(query);
        List<Announcements> announcementsList = mapper.query(Announcements.class, queryExpression);
        return announcementsList.size() == 0 ? null : announcementsList.get(0);
    }

    public List<Announcements> getAllAnnouncements() {
        return mapper.scan(Announcements.class, new DynamoDBScanExpression());
    }

    public Announcements addAnnouncements(Announcements announcements) {
        if(announcements == null){
            return null;
        }
        mapper.save(announcements);
        return announcements;
    }

    public Announcements deleteAnnouncements(String boardId, String announcementId) {
        Announcements announcements = getAnnouncements(boardId, announcementId);
        if(announcements == null){
            return null;
        }
        mapper.delete(announcements);
        return announcements;
    }

    public Announcements updateAnnouncements(String boardId, String announcementId, Announcements announcements) {
        deleteAnnouncements(boardId, announcementId);
        announcements.setAnnouncementId(announcementId);
        announcements.setBoardId(boardId);
        mapper.save(announcements);
        return announcements;
    }




}
