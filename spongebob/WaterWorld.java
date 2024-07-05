import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.*; 
/**
 * Write a description of class WaterWorld here.
 * 
 */
public class WaterWorld extends World
{
    
    private ArrayList<String> texts = new ArrayList<>();
    private ArrayList<Integer> scores = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private int timer = 0;
    private int time = 1000;
    public Boolean again = false;
    String name = "User";
    /**
     * Constructor for objects of class WaterWorld.
     * 
     */
    public WaterWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        prepare();
        Greenfoot.setSpeed(50); // Adjust game speed
        
        // Add initial text elements
        texts.add("Score: 0");
        texts.add("Speed: 1");
        texts.add("Name: " + name);
        texts.add("Time: " + time);
        updateTexts();
    }
    
    public void act()
    {
        Sbob sponge = new Sbob();
        if (timer % 60 == 0) { // Adjust timing
            dropGold();
        }
        if (timer % 180 == 0) { // Adjust timing
            dropKrabbyPatty();
        }
        if (timer % 360 == 0) { // Adjust timing
            dropCrabTrap();
        }
        timer++;
        time--;
        if (time == 0) {
            showText("Game Over!", 400, 300);
            Boolean check = true;
            Boolean again = false;
            while (check)
            {
                String input = Greenfoot.ask("Do you wish to play again (yes or no)");
                if (input.toUpperCase().equals("YES")) {
                    showText("", 0, 0);
                    check = false;
                    again = true;
                }
                else if (input.toUpperCase().equals("NO")){
                    check = false;
                   again = false;
                }
                continue;
            }
            if (!again){
                scores.add(sponge.score);
                names.add(name);
                HashMap<Integer, String> lb = new HashMap<Integer, String>();
                for (int i=0; i< names.size();i++){
                    lb.put(scores.get(i),names.get(i));
                }
                Collections.sort(scores, Collections.reverseOrder());
                showText("High Scores", 700, 20);
                
                Integer i =0;
                for (Integer score: scores) {
                    showText(lb.get(score), 700, 50+(i*20));
                    showText(score.toString(), 750, 50+(i*20));
                    i++;
                }
                Greenfoot.stop();
            }
            else {
                /*time = 500;
                // clear Game Over text
                showText("", 400, 300);
                updateText("Score: 0", 100, 20, "score");
                updateText("Speed: 1", 700, 20, "speed");
                updateText("Name: User" , 100, 20, "name");*/
                scores.add(sponge.score);
                names.add(name);
                started();
                
            }
        }
        updateText("Time: " + time, 100, 20, "time");
    }

    public void started()
    {
        Sbob sponge = new Sbob();
        sponge.speed = 1;
        sponge.score = 0;
        time = 1000;
        // clear Game Over text
        showText("", 400, 300);
        updateText("Score: 0", 100, 20, "score");
        updateText("Speed: 1", 700, 20, "speed");
        updateText("Name: User" , 100, 20, "name");
        Boolean check = true;
        
        while (check)
        {
            String input = Greenfoot.ask("Enter your name");
            if (input == null || "".equals(input))  continue;
            name = input;
            updateText("Name: " + name, 100, 20, "name");
            check = false;
        }
    }
    
    public void dropGold(){
        PirateGold g = new PirateGold();
        addObject(g, Greenfoot.getRandomNumber(getWidth() - 100), 10);
    }
    
    public void dropKrabbyPatty(){
        KrabbyPatty k = new KrabbyPatty();
        addObject(k, 50 + Greenfoot.getRandomNumber(getWidth() - 100), 10);
    }
    
    public void dropCrabTrap(){
        CrabTrap t = new CrabTrap();
        addObject(t, 50 + Greenfoot.getRandomNumber(getWidth() - 100), 10);
    }
    
    public void prepare()
    {
        Sbob sbob = new Sbob();
        addObject(sbob, 399, 527);
    }
    
    public void updateText(String text, int x, int y, String identifier) {
        if (identifier.equals("score")) {
            texts.set(0, text);
        } else if (identifier.equals("speed")) {
            texts.set(1, text);
        }
        else if (identifier.equals("name")) {
            texts.set(2, text);
        }
        else if (identifier.equals("time")) {
            texts.set(3, text);
        }
        updateTexts();
    }
    
    private void updateTexts() {
        // Clear previous text
        showText("", 0, 0);
        
        // Display all texts
        for (int i = 0; i < texts.size(); i++) {
            showText(texts.get(i), 100 + i * 150, 20);
        }
    }
}