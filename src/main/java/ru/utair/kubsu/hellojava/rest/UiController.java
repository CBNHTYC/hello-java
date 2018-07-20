package ru.utair.kubsu.hellojava.rest;

import com.google.common.cache.Cache;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.utair.kubsu.hellojava.database.JokeDao;
import ru.utair.kubsu.hellojava.model.Joke;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController //аннотация bin, rest
@RequestMapping(path="/rest",produces="application/json") //produces - методы будут отдавать json
public class UiController implements HasLogger{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Cache<ObjectId, Joke> cache;

    @Autowired
    JokeDao jokeDao;


    @GetMapping(path="/text")
    public String text1() {
        return "kek";
    }

    @GetMapping(path="/getJoke")
    public ResponseEntity<Joke> getJoke(){

        try {
            String url = "http://umorili.herokuapp.com/api/random?num=100";
            List<Map<String, String>> result = restTemplate.getForObject(url, List.class);

            Random rand = new Random();
            int index = rand.nextInt(result.size());

            Joke joke = new Joke();
            joke.setId(new ObjectId());
            joke.setCategory(result.get(index).get("desc"));
            joke.setSite(result.get(index).get("site"));
            joke.setText(result.get(index).get("elementPureHtml"));


            ObjectId id = new ObjectId();
            joke.setId(id);

            cache.put(id, joke);
            return new ResponseEntity(joke, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "add")
    public void add(@RequestBody String id) {
        jokeDao.addJoke(cache.getIfPresent(new ObjectId(id)));

    }

    @GetMapping(path="/getAll")
    public List<Joke> getAllJokes(){
        getLogger().info("Start getting all jokes.");
        return jokeDao.getAllJokes();

    }
}
