/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genetics;

import Genetics.Chromosome;
import Genetics.ConnectionGene;
import Genetics.Gene;
import Genetics.Genome;
import Genetics.SyncGenome;
import Genetics.InnovationDB;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author shanemendez
 */
public class SyncGenome extends Genome{
    
    // These should be private
    public Random random = new Random();
    public Chromosome<Gene> chromosome;
    private int nodes = 1;
    private InnovationDB db;
    private double mrate = .2;
    private double srate = .3;

    public SyncGenome(int nodes, InnovationDB db) {
        this(new Chromosome(), nodes, db);
    }

    public SyncGenome(Chromosome chromosome, int nodes, InnovationDB db) {
        this.chromosome = chromosome;
        this.nodes = nodes;
        this.db = db;
    }

    public SyncGenome addNode() {
        return new SyncGenome(chromosome, nodes + 1, db);
    }
    public SyncGenome addAll(ArrayList<Gene> genes){
        SyncGenome g = new SyncGenome(chromosome,nodes,db);
        for(Gene gene: genes){
            g = g.add(gene);
        }
        return g;
    }
    public SyncGenome add(Gene g) {
        int id = db.get(g);
        g.setId(id);
        return new SyncGenome(chromosome.add(g), nodes, db);
    }
    public SyncGenome remove(Gene g){
        Chromosome temp = new Chromosome(chromosome.genes);
        temp = temp.remove(g);
        return new SyncGenome(temp);
        
    }
    @Override
    public String toString() {
        return nodes + ", " + chromosome.toString();
    }

    @Override
    public SyncGenome clone() {
        return new SyncGenome(this.chromosome, nodes, db);
    }

    /**
     * Only parts that cannot be separated from ConnectionGene Splitting is a
     * feature of only connection genes maybe in the future we can make this
     * less specific
     *
     * @return
     */
    public SyncGenome split(ConnectionGene c) {
        SyncGenome g = new SyncGenome(new Chromosome(), nodes, db);
        
        return g;
    }

    public SyncGenome mutate() {
        SyncGenome genome = new SyncGenome(this.chromosome.mutate(mrate), nodes, db);
        if (this.srate < this.random.nextDouble()) {
            
        }

        return genome;

    }
}
