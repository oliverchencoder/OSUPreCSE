package com.demo.colin.demo;

import java.util.ArrayList;
import java.util.Set;

public class CourseTree {

    private ArrayList<Course> courses;


    public CourseTree(String major) {
        if (major.equals("Pre")) {
            this.courses = preTreeBuild();
        } else if (major.equals("AI")) {
            //this.courses = AITreeBuild();
        }
    }


    private static ArrayList<Course> preTreeBuild() {
        ArrayList<Course> res = new ArrayList<>();
        ArrayList<String> pre = new ArrayList<>();
        ArrayList<String> sub = new ArrayList<>();
        sub.add("ENG1182");
        res.add(new Course("ENG1181", pre, sub));
        arrayClear(pre, sub);
        pre.add("ENG1181");
        res.add(new Course("ENG1182", pre, sub));

        arrayClear(pre, sub);
        sub.add("ENG1902");
        res.add(new Course("ENG1901", pre, sub));
        arrayClear(pre, sub);
        pre.add("ENG1901");
        sub.add("ENG1110");
        res.add(new Course("ENG1902", pre, sub));
        arrayClear(pre, sub);
        pre.add("ENG1902");
        res.add(new Course("ENG1110", pre, sub));


        arrayClear(pre, sub);
        res.add(new Course("MATH1151", pre, sub));

        arrayClear(pre, sub);
        sub.add("PHY1251");
        res.add(new Course("PHY1250", pre, sub));
        arrayClear(pre, sub);
        pre.add("PHY1250");
        res.add(new Course("PHY1251", pre, sub));


        arrayClear(pre, sub);
        sub.add("CSE2221");
        res.add(new Course("CSE1223", pre, sub));
        arrayClear(pre, sub);
        pre.add("CSE1223");
        res.add(new Course("CSE2221", pre, sub));

        return res;
    }

    private static void arrayClear(ArrayList<String> pre, ArrayList<String> sub) {
        pre.clear();
        sub.clear();

    }

    public ArrayList<String> getChoice() {
        ArrayList<String> res = new ArrayList<>();
        for (Course c : this.courses) {
            if (c.getPre().size() == 0) {
                res.add(c.getName());
            }
        }
        return res;
    }

    public void deletClass(String name) {
        int i = 0;
        while (!this.courses.get(i).getName().equals(name)) {
            i++;
        }

        ArrayList<String> sub = this.courses.get(i).getSub();

        for (String subName : sub) {
            int j = 0;
            while (!this.courses.get(j).getName().equals(subName)) {
                j++;
            }
            this.courses.get(j).deletePre(name);
        }

        this.courses.remove(i);
    }


    public void deleteAll(Set<String> set) {
        for (String name : set) {
            int i = 0;
            // Get the course node
            while (!this.courses.get(i).getName().equals(name)) {
                i++;
            }
            // Get the next course list for this specific course node
            ArrayList<String> sub = this.courses.get(i).getSub();
            // Delete all the prerequisite of all the next courses
            for (String subName : sub) {
                int j = 0;
                // Get one course node from the next course lists
                while (!this.courses.get(j).getName().equals(subName)) {
                    j++;
                }
                // Update nextNode.pre by deleting the course node set.name
                this.courses.get(j).deletePre(name);
            }

            // Get the pre course list for this specific course node
            ArrayList<String> pre = this.courses.get(i).getPre();
            // Delete all the prerequisite of all the next courses
            for (String subName : pre) {
                int j = 0;
                // Get one course node from the next course lists
                while (!this.courses.get(j).getName().equals(subName)) {
                    j++;
                }
                // Update nextNode.pre by deleting the course node set.name
                this.courses.get(j).deleteSub(name);
                if (this.courses.get(j).getSub().size()==0) {
                    this.courses.remove(j);
                }
            }
            this.courses.remove(i);
        }
    }

}