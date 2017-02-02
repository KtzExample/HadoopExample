package org.ktz.example.hadoopexample;

import org.apache.avro.Schema;
import org.apache.avro.mapred.*;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.File;
import java.io.IOException;

/**
 * Created by ktz on 17. 2. 3.
 */
public class AvroSort extends Configured implements Tool {
    static class SortMapper<K> extends AvroMapper<K, Pair<K, K>> {
        @Override
        public void map(K datum, AvroCollector<Pair<K, K>> collector,
                        Reporter reporter) throws IOException {
            collector.collect(new Pair<K, K>(datum, null, datum, null));
        }
    }

    static class SortReducer<K> extends AvroReducer<K, K, K> {
        @Override
        public void reduce(K key, Iterable<K> values,
                           AvroCollector<K> collector,
                           Reporter reporter) throws IOException {
            for(K value : values) {
                collector.collect(value);
            }
        }
    }

    public int run(String[] args) throws Exception {
        if(args.length != 3){
            System.err.printf(
                    "Usage: %s [generic options] <input> <output> <schema-file>\n",
                    getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }

        String input = args[0];
        String output = args[1];
        String schemaFile = args[2];

        JobConf conf = new JobConf(getConf(), getClass());
        conf.setJobName("Avro sort");

        FileInputFormat.addInputPath(conf, new Path(input));
        FileOutputFormat.setOutputPath(conf, new Path(output));

        Schema schema = new Schema.Parser().parse(new File(schemaFile));
        AvroJob.setInputSchema(conf, schema);
        Schema intermediateSchema = Pair.getPairSchema(schema, schema);
        AvroJob.setMapperClass(conf, SortMapper.class);
        AvroJob.setReducerClass(conf, SortReducer.class);

        JobClient.runJob(conf);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new AvroSort(), args);
        System.exit(exitCode);
    }
}