package csen1002.main.task7;


import java.util.*;

/**
 * Write your info here
 *
 * @name Jane Smith
 * @id 46-0234
 * @labNumber 07
 */

public class CfgLl1Parser {
    String[] variables;
    String[] characters;

    String Sequences;

    ArrayList<String> sequence = new ArrayList<>();

    ArrayList<String> Prods;

    String Allfirst;
    ArrayList<String> First;

    ArrayList<String> Follow;
    String Allfollow;

    HashMap<String, ArrayList<String>> map = new HashMap<>();


    /**
     * Constructs a Context Free Grammar
     * <p>
     * //  * @param cfg A formatted string representation of the CFG, the First sets of
     * each right-hand side, and the Follow sets of each variable. The
     * string representation follows the one in the task description
     */
    public CfgLl1Parser(String input) {

        String[] x = input.split("#");
        variables = x[0].split(";");
        characters = x[1].split(";");
        Sequences = x[2];
        String[] x2 = Sequences.split(";");
        Prods = new ArrayList<>();
        for (int i = 0; i < x2.length; i++) {
            Prods.add(x2[i]);
        }
        System.out.println("ppp" + Prods);
        Allfirst = x[3];
        String[] x3 = Allfirst.split(";");
        First = new ArrayList<>();
        for (int i = 0; i < x3.length; i++) {
            First.add(x3[i]);
        }
        Allfollow = x[4];
        String[] x4 = Allfollow.split(";");
        Follow = new ArrayList<>();
        for (int i = 0; i < x4.length; i++) {
            Follow.add(x4[i]);
        }
        //System.out.println(Allfirst);
        //    System.out.println(Allfollow);
        //   System.out.println(Follow);


        // TODO Auto-generated constructor stub
    }

    /**
     * @param input The string to be parsed by the LL(1) CFG.
     * @return A string encoding a left-most derivation.
     */
    public String parse(String input) {
        ArrayList<String> q = new ArrayList<>();
        HashMap<String, HashMap<String, String>> map = new HashMap<>();
        for (String var : variables) {
            HashMap<String, String> mapx = new HashMap<>();
            map.put(var, mapx);

            for (String ter : characters) {
                // HashMap<String, String> mapx = new HashMap<>();
                mapx.put(ter, null);
            }
            mapx.put("$", null);
        }

        String rules = "";
        for (int i = 0; i < variables.length; i++) {
            String s = Prods.get(i);
            String[] arr = s.split("/");
            // System.out.println(Arrays.toString(arr));
            //System.out.println("arrvar"+arrvar[i]);
            String variable = arr[0];
            rules = arr[1];
            String[] arr2 = rules.split(",");
            //  ArrayList<String> arrayrules = new ArrayList<String>(List.of(arr2));
            System.out.println("arr2" + Arrays.toString(arr2));

            String first = "";

            String s1 = First.get(i);
            String[] arrf = s1.split("/");
            // System.out.println(Arrays.toString(arr));
            //System.out.println("arrvar"+arrvar[i]);
            variable = arr[0];
            first = arrf[1];
            String[] arr3 = first.split(",");
            // ArrayList<String> arrayfirst = new ArrayList<String>(List.of(arr));
            System.out.println("arr2" + Arrays.toString(arr2));

            for (int j = 0; j < arr2.length; j++) { //looping on  rules and first
                String r = arr2[j];
                String f = arr3[j];
                for (int k = 0; k < f.length(); k++) {
                    if (String.valueOf(f.charAt(k)).equals("e")) {
                        String fol = Follow.get(i); //getting the element at position i
                        String[] arrfol = fol.split("/");
                        //   String variabl = arr[0];
                        String follo = arrfol[1];
                        for (int l = 0; l < follo.length(); l++) { //looping on element in follow
                            map.get(variable).put(String.valueOf(follo.charAt(l)), r);

                        }

                    } else
                        map.get(variable).put(String.valueOf(f.charAt(k)), r);
                }

            }


        }
        System.out.println("map" + map);
        Stack<String> x = new Stack<>();
        input +="$";
        String output = "";
        StringBuilder currentstate = new StringBuilder();
        x.push("$");
        x.push("S");
        currentstate.append("S");
        output += "S";
        int p = 0;
        ArrayList<String> variables1 = new ArrayList<String>(List.of(variables));
        while (!x.isEmpty()) {
        //    System.out.println("ouy"+output);
            if (variables1.contains(x.peek())) {
                String var = x.pop();
                String rule = map.get(var).get(String.valueOf(input.charAt(p)));
                if (rule == null) {
                    output += ";"+ "ERROR";
                    break;
                } else {
                    if(!rule.equals("e")){
                    for (int i = rule.length() - 1; i >= 0; i--) {
                        x.push(String.valueOf(rule.charAt(i)));
                    }
                    currentstate.replace(p,p+1,rule);
                    output += ";"+ currentstate;
                }
                    else {
                        currentstate.delete(p,p+1);
                        output += ";"+ currentstate;
                    }
                }


            }
            else {
                if(String.valueOf(input.charAt(p)).equals(x.peek())){
                    x.pop();
                    p++;


                }
                else {
                    output += ";"+ "ERROR";
                            break;
            }}

        }


        // TODO Auto-generated method stub
        return output;
    }

    public static void main(String[] args) {
        String cfg = ("S;T#a;c;i#S/iST,e;T/cS,a#S/i,e;T/c,a#S/$ca;T/$ca");
        CfgLl1Parser mycfg = new CfgLl1Parser(cfg);
        String input = ("iia");
        mycfg.parse(input);
        String c=mycfg.parse(input);
        System.out.println("output"+c);


    }
}