package in.co.hayden.WebScraper.SearchCount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "https://webscraper.hayden.co.in/"})

public class SearchCountController {
    @Autowired
    private SearchCountService searchCountService;
    @PostMapping("/insertSearchCount")
    public ResponseEntity<SearchCount> insertSearchTerm(@RequestBody String searchTerm) {
        String searchTermWithoutQuotes = searchTerm.replace("\"", "");
        searchCountService.insertSearchTerm(searchTermWithoutQuotes);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/searchCount")
    public ResponseEntity<SearchCount> incrementSearchCount(@RequestBody String searchTerm) {
        String searchTermWithoutQuotes = searchTerm.replace("\"", "");
        searchCountService.insertSearchCount(searchTermWithoutQuotes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/topSearchTerms")
    public ResponseEntity<List<SearchCount>> incrementSearchCount() {
        // Fetch all search counts from the database
        List<SearchCount> allSearchCounts = searchCountService.topSearch();

        // Create a max heap using PriorityQueue with a custom comparator
        PriorityQueue<SearchCount> maxHeap = new PriorityQueue<>(Comparator.comparingInt(SearchCount::getSearchCount).reversed());
        maxHeap.addAll(allSearchCounts);

        // Retrieve the top 10 elements from the max heap
        List<SearchCount> topSearchTerms = new ArrayList<>();
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            topSearchTerms.add(maxHeap.poll());
            count++;
        }

//        return topSearchTerms;
        return new ResponseEntity<>(topSearchTerms, HttpStatus.OK);

    }
}