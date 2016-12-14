package Genetics;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.Stack;

public class Genome {



    // These should be private
    public Random random = new Random();
    public Chromosome<Gene> chromosome;


    public Genome() {
        this.chromosome = new Chromosome();
    }

    public Genome(Chromosome chromosome) {
        this.chromosome = chromosome;
    }


    public Genome remove(Gene g) {
        chromosome.remove(g);
        return this;
    }

    @Override
    public String toString() {
        return chromosome.toString();
    }

    /**
     * Only parts that cannot be separated from ConnectionGene Splitting is a
     * feature of only connection genes maybe in the future we can make this
     * less specific
     *
     * @param c
     * @return
     */

    public Genome crossover(Genome g){
        return new Genome((this.chromosome.crossover(g.chromosome)));
    }
    public Genome mate(Genome mate){
        return this.crossover(mate).mutate();
    }

    public Genome mutate() {
        this.chromosome.mutate();
        return this;

    }

    @Override
    public Genome clone() {
        return new Genome(this.chromosome.clone());
    }

    public boolean equals(Object o) {
        Genome c = (Genome) o;
        return c.chromosome.equals(this.chromosome);
    }

    public Gene getGene(int i){
        return chromosome.get(i);
    }
    public int size(){
        return chromosome.size();
    }

}
