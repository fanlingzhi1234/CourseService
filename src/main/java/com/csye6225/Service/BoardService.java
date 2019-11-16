package com.csye6225.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.datamodel.Board;
import com.csye6225.datamodel.DynamoDbConnector;


import java.util.HashMap;
import java.util.List;
/**
 * @author Rexus
 * @date 11/10/19
 */
public class BoardService {


    static DynamoDbConnector dynamoDb;
    DynamoDBMapper mapper;

    public BoardService() {
        dynamoDb = new DynamoDbConnector();
        dynamoDb.init();
        mapper = new DynamoDBMapper(dynamoDb.getClient());
    }

    public Board getBoard(String boardId) {
        HashMap<String, AttributeValue> query = new HashMap<>();
        query.put(":attribute1", new AttributeValue().withS(boardId));
        DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
                .withIndexName("boardId-index")
                .withConsistentRead(false)
                .withKeyConditionExpression("boardId = :attribute1")
                .withExpressionAttributeValues(query);
        List<Board> boardList= mapper.query(Board.class, queryExpression);
        return boardList.size() == 0 ? null : boardList.get(0);
    }

    public List<Board> getAllBoards() {
        return mapper.scan(Board.class, new DynamoDBScanExpression());
    }

    public Board addBoard(Board board) {
        if(board == null){
            return null;
        }
        mapper.save(board);
        return board;
    }

    public Board deleteBoard(String boardId) {
        Board board = getBoard(boardId);
        if(board == null) {return null;}
        mapper.delete(board);
        return board;
    }

    public Board updateBoard(String boardId, Board board) {
        deleteBoard(boardId);
        board.setBoardId(boardId);
        mapper.save(board);
        return board;
    }
}
