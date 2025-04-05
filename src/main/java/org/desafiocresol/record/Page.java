package org.desafiocresol.record;

import java.util.List;

public record Page<T> (
        List<T> data,
        long total,
        boolean hasNextPage
)
{}
