package org.ktz.example.hadoopexample;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

/**
 * Created by ktz on 2017. 1. 17..
 */
public class ListStatus {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        Path[] paths = new Path[args.length];
        for(int i = 0; i < paths.length; i++){
            paths[i] = new Path(args[i]);
        }
    }
}
