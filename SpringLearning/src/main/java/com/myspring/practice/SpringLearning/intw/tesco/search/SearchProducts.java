package com.myspring.practice.SpringLearning.intw.tesco.search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class SearchProducts {

    private static final String SEARCH_TERM = "Chocolate Milk";

    static void main() {
        List<ProductItem> productItems = List.of(
                new ProductItem(1, "Amul milk"),
                new ProductItem(2, "Nandini Chocolate Milk"),
                new ProductItem(3, "Milk Chocolate"),
                new ProductItem(4, "Chocolate Milk"),
                new ProductItem(5, "Milky Way Chocolate Bar")
        );

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        SearchRule searchRule = new WordMatchRule(SEARCH_TERM);
        for (int i = 0; i < productItems.size(); i++) {
            int finalI = i;
            CompletableFuture<Boolean> future = CompletableFuture
                    .supplyAsync(() -> searchRule.matches(productItems.get(finalI)), executorService);
            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        // 2. Chain a block to extract and collect results once ready
        CompletableFuture<List<Boolean>> combinedFuture = allOf.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join) // Non-blocking because allOf guaranteed completion
                        .collect(Collectors.toList())
        );

        // 5. Unpack the background results using .join()
        List<Boolean> matchResults = combinedFuture.join();

        // 6. Filter and print only the items that matched the rule
        for (int i = 0; i < productItems.size(); i++) {
            if (matchResults.get(i)) {
                ProductItem item = productItems.get(i);
                System.out.println(item.id() + "," + item.description());
            }
        }

        // 7. Always shut down your executor pool to prevent the application from hanging
        executorService.shutdown();
    }
}
