package org.ktz.example.hadoopexample;

import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * Created by ktz on 2017. 1. 20..
 */
public class MaxTemperatureWithCompression {
    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.err.println("Args not enough");
            System.exit(-1);
        }

        Job job = new Job();
    }
}
