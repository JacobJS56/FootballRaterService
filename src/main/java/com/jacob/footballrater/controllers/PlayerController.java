package com.jacob.footballrater.controllers;

import com.jacob.footballrater.dtos.PlayerDto;
import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.services.PlayerService;
import com.jacob.footballrater.mapper.MapStructMapperImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v2/player")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final MapStructMapperImpl mapStructMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable("id") UUID id) {

        Player player = playerService.getPlayer(id);
        PlayerDto playerResponse =  mapStructMapper.playerToPlayerDto(player);

        return new ResponseEntity<>(playerResponse, HttpStatus.OK);
    }

    @GetMapping("/all/{team-name}")
    public ResponseEntity<List<PlayerDto>> getAllPlayersByTeam(@PathVariable("team-name") String teamName) {

        List<Player> playerList = playerService.getAllPlayersByTeamName(teamName);
        List<PlayerDto> response = new ArrayList<>();

        for(Player p: playerList)
            response.add(mapStructMapper.playerToPlayerDto(p));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/top-20")
    public ResponseEntity<List<PlayerDto>> getAllTeamsByLeague() {

        List<Player> playerList = playerService.getTop20Players();
        List<PlayerDto> response = new ArrayList<>();

        for(Player p: playerList)
            response.add(mapStructMapper.playerToPlayerDto(p));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto){

        Player playerRequest = mapStructMapper.playerDtoToPlayer(playerDto);
        Player player = playerService.createPlayer(playerRequest);
        PlayerDto playerResponse =  mapStructMapper.playerToPlayerDto(player);

        return new ResponseEntity<>(playerResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDto> updateRating(@PathVariable("id") UUID id, @RequestBody PlayerDto playerDto) {

        Player playerRequest = mapStructMapper.playerDtoToPlayer(playerDto);
        Player player = playerService.updateRating(id, playerRequest);
        PlayerDto playerResponse =  mapStructMapper.playerToPlayerDto(player);

        return new ResponseEntity<>(playerResponse, HttpStatus.CREATED);
    }

}
