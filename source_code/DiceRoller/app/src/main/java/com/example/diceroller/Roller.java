package com.example.diceroller;

import java.util.ArrayList;

public class Roller {
    int num_hits;
    ArrayList<Integer> hits;
    int num_crits;
    ArrayList<Integer> crits;
    int num_crit_fails;
    ArrayList<Integer> crit_fails;
    int num_min_rolls;
    int num_max_rolls;
    int damage;

    public Roller() {}

    public int roll(int die_type) {
        double roll = Math.random() * (die_type) + 1;
        return (int)roll;
    }


    public int roll_with_advantage(int die_type) {
        int roll_one = roll(die_type);
        int roll_two = roll(die_type);

        if(roll_one >= roll_two) { return roll_one; }
        else { return roll_two; }
    }

    public int roll_with_disadvantage(int die_type) {
        int roll_one = roll(die_type);
        int roll_two = roll(die_type);

        if(roll_one <= roll_two) { return roll_one; }
        else { return roll_two; }
    }

    public String run(int num_rolls, int dc_check, int bonus, boolean advantage, boolean disadvantage) {
        String output = "";

        num_hits = 0;
        hits = new ArrayList<Integer>();
        num_crits = 0;
        crits = new ArrayList<Integer>();
        num_crit_fails = 0;
        crit_fails = new ArrayList<Integer>();

        int r = 0;

        if(advantage) {
            for(int i = 1; i <= num_rolls; ++i) {
                r = roll_with_advantage(20);
                if(r + bonus >= dc_check && r != 1) {
                    num_hits++;
                    hits.add(i);
                }
                if(r == 20) {
                    num_crits++;
                    crits.add(i);
                }
                if(r == 1) {
                    num_crit_fails++;
                    crit_fails.add(i);
                }
            }
        } else if (disadvantage) {
            for(int i = 1; i <= num_rolls; ++i) {
                r = roll_with_disadvantage(20);
                if(r + bonus >= dc_check && r != 1) {
                    num_hits++;
                    hits.add(i);
                }
                if(r == 20) {
                    num_crits++;
                    crits.add(i);
                }
                if(r == 1) {
                    num_crit_fails++;
                    crit_fails.add(i);
                }
            }
        } else {
            for(int i = 1; i <= num_rolls; ++i) {
                r = roll(20);
                if(r + bonus >= dc_check && r != 1) {
                    num_hits++;
                    hits.add(i);
                }
                if(r == 20) {
                    num_crits++;
                    crits.add(i);
                }
                if(r == 1) {
                    num_crit_fails++;
                    crit_fails.add(i);
                }
            }
        }

        output += "Number of Hits: " + String.valueOf(num_hits) + "\n";
        output += "Number of Crits: " + String.valueOf(num_crits) + "\n";
        if(crits.size() > 0) output += "Rolls that Crit: " + crits.toString() + "\n";
        output += "Number of Crit Fails: " + String.valueOf(num_crit_fails) + "\n";
        if(crit_fails.size() > 0) output += "Rolls that Crit Failed: " + crit_fails.toString() + "\n";

        return output;
    }

    public String run(int num_rolls, int dc_check, int hit_bonus, int die_type, int dmg_bonus, boolean advantage, boolean disadvantage) {
        String output = "";

        num_max_rolls = 0;
        num_min_rolls = 0;
        damage = 0;

        int d = 0;

        output = run(num_rolls, dc_check, hit_bonus, advantage, disadvantage);

        for(int i = 1; i <= 100; ++i) {
            if(hits.contains(i)) {
                d = roll(die_type);

                if(d == 1) { num_min_rolls++; }
                else if(d == die_type) { num_max_rolls++; }

                if(crits.contains(i)) { d *= 2; }

                damage += d + dmg_bonus;
            }
        }

        output += "\n";
        output += "Total Damage: " + String.valueOf(damage) + "\n";
        output += "Number of Min Rolls: " + String.valueOf(num_min_rolls) + "\n";
        output += "Number of Max Rolls: " + String.valueOf(num_max_rolls);

        return output;
    }
}
