package com.csye6225.Api;

import com.csye6225.datamodel.Board;
import com.csye6225.Service.BoardService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Rexus
 * @date 11/10/19
 */
@Path("board")
public class BoardResource {
    BoardService boardService = new BoardService();

    public BoardResource(){

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Board addBoard(Board board) {
        return boardService.addBoard(board);
    }

    @GET
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Board getBoard(@PathParam("boardId") String boardId) {
        return boardService.getBoard(boardId);
    }

    @DELETE
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Board deleteBoard(@PathParam("boardId") String boardId) {
        return boardService.deleteBoard(boardId);
    }

    @PUT
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Board updateBoard(@PathParam("boardId") String boardId, Board board) {
        return boardService.updateBoard(boardId, board);
    }
}
