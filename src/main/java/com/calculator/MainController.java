package com.calculator;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.*;
import java.util.Scanner;

public class MainController {

    @FXML
    private TextField BadgePer;

    @FXML
    private TextField BadgePricePer;

    @FXML
    private AnchorPane anchorPaneMain;

    @FXML
    private AnchorPane anchorPaneSettings;

    @FXML
    private TextField animatedPrice;

    @FXML
    private TextField badgePrice;

    @FXML
    private TextField buysAnimated;

    @FXML
    private TextField buysBadges;

    @FXML
    private TextField buysEmotes;

    @FXML
    private Text buysSum;

    @FXML
    private TextField emorePerSpecial;

    @FXML
    private TextField emotePer;

    @FXML
    private TextField emotePrice;

    @FXML
    private TextField emotePricePer;

    @FXML
    private TextField emoteSpecialOffer;

    @FXML
    private TextField name;

    int costEmotes = 80;
    int costEmotesProm = 220;
    int emotesPer = 3;
    int costAnimated = 120;
    int costBadge = 20;
    int costBadgeProm = 100;
    int badgesPer = 3;
    int howMuchToPromotion = 5;
    int promo = 15;

    public int getBuysEmotes(){
        if(buysEmotes.getText().isBlank()) return 0;
        return Integer.valueOf(buysEmotes.getText().trim());
    }
    public int getBuysBadges(){
        if(buysBadges.getText().isBlank()) return 0;
        return Integer.valueOf(buysBadges.getText().trim());
    }

    public int getBuysAnimated(){
        if(buysAnimated.getText().isBlank()) return 0;
        return Integer.valueOf(buysAnimated.getText().trim());
    }

    public void initialize(){
        buysAnimated.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    buysAnimated.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        buysEmotes.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    buysEmotes.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        buysBadges.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    buysBadges.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        readDate();
    }

    private void readDate(){
        String data = "";
        try {
            File myObj = new File("settings.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String[] split = data.split("\n");
        if(split.length > 1) {
            if (!split[0].isBlank()) costEmotes = Integer.valueOf(split[0]);
            if (!split[1].isBlank()) costBadge = Integer.valueOf(split[1]);
            if (!split[2].isBlank()) costAnimated = Integer.valueOf(split[2]);
            if (!split[3].isBlank()) emotesPer = Integer.valueOf(split[3]);
            if (!split[4].isBlank()) costEmotesProm = Integer.valueOf(split[4]);
            if (!split[5].isBlank()) badgesPer = Integer.valueOf(split[5]);
            if (!split[6].isBlank()) costBadgeProm = Integer.valueOf(split[6]);
            if (!split[7].isBlank()) howMuchToPromotion = Integer.valueOf(split[7]);
            if (!split[8].isBlank()) promo = Integer.valueOf(split[8]);
        }

       /* JSONParser jsonParser = new JSONParser();
        File f = new File("settings.json");
        if(!f.exists()) return;
        try (FileReader reader = new FileReader("settings.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject cost = (JSONObject) obj;
            if(cost.get("EmotesPrice") != "") costEmotes = Integer.valueOf(cost.get("EmotesPrice").toString());
            if(cost.get("BadgePrice") != "") costBadge = Integer.valueOf(cost.get("BadgePrice").toString());
            if(cost.get("AnimatedEmotesPrice") != "") costAnimated = Integer.valueOf(cost.get("AnimatedEmotesPrice").toString());
            if(cost.get("EmotePer") != "") emotesPer = Integer.valueOf(cost.get("EmotePer").toString());
            if(cost.get("EmotePricePer") != "") costEmotesProm = Integer.valueOf(cost.get("EmotePricePer").toString());

            if(cost.get("BadgePer") != "") badgesPer = Integer.valueOf(cost.get("BadgePer").toString());
            if(cost.get("BadgePricePer") != "") costBadgeProm = Integer.valueOf(cost.get("BadgePricePer").toString());

            if(cost.get("EmotePerSpecial") != "") howMuchToPromotion = Integer.valueOf(cost.get("EmotePerSpecial").toString());
            if(cost.get("EmoteSpecialOffer") != "") promo = Integer.valueOf(cost.get("EmoteSpecialOffer").toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    void back(ActionEvent event) {
        anchorPaneMain.setVisible(true);
        anchorPaneSettings.setVisible(false);
        readDate();
        calculatePrice();
    }

    @FXML
    void saveSettings() throws IOException {

        PrintWriter writer = new PrintWriter("settings.txt");
        writer.print("");
        writer.close();

        try(FileWriter fileWriter = new FileWriter("settings.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter))
        {

            printWriter.println(emotePrice.getText());
            printWriter.println(badgePrice.getText());
            printWriter.println(animatedPrice.getText());

            printWriter.println(emotePer.getText());
            printWriter.println(emotePricePer.getText());

            printWriter.println(BadgePer.getText());
            printWriter.println(BadgePricePer.getText());

            printWriter.println(emorePerSpecial.getText());
            printWriter.println( emoteSpecialOffer.getText());
            printWriter.println(name.getText());
            printWriter.close();
        } catch (IOException e) {

        }

       /* JSONObject jsonObject = new JSONObject();

        jsonObject.put("EmotesPrice", emotePrice.getText());
        jsonObject.put("BadgePrice", badgePrice.getText());
        jsonObject.put("AnimatedEmotesPrice", animatedPrice.getText());

        jsonObject.put("EmotePer", emotePer.getText());
        jsonObject.put("EmotePricePer", emotePricePer.getText());

        jsonObject.put("BadgePer", BadgePer.getText());
        jsonObject.put("BadgePricePer", BadgePricePer.getText());

        jsonObject.put("EmotePerSpecial", emorePerSpecial.getText());
        jsonObject.put("EmoteSpecialOffer", emoteSpecialOffer.getText());

        FileWriter file = new FileWriter("settings.json");
        file.write(jsonObject.toJSONString());
        file.close();*/
    }

    double sumAll = 0;
    @FXML
    void saveDate(ActionEvent event) {
        try(FileWriter fileWriter = new FileWriter("myfile.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter))
        {
            /*Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            printWriter.println(formatter.format(date) + " " + name.getText());*/
            printWriter.println(name.getText());
            printWriter.println("\t Emotes \t\t\t" + getBuysEmotes() + "\t" + sumCost(buysEmotes,costEmotes,costEmotesProm));
            printWriter.println("\t Badges \t\t\t" + getBuysBadges() + "\t" + sumCost(buysBadges,costBadge,costBadgeProm));
            printWriter.println("\t Animated Emotes \t\t" + getBuysAnimated() + "\t" + sumCost(buysAnimated,costAnimated,costAnimated));
            printWriter.println("\t Sum \t\t\t\t\t" + sumAll);
        } catch (IOException e) {

        }
    }
    @FXML
    void settings(ActionEvent event) {
       anchorPaneMain.setVisible(false);
       anchorPaneSettings.setVisible(true);
        String data = "";
        try {
            File myObj = new File("settings.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String[] split = data.split("\n");
        emotePrice.setText(split[0]);
        badgePrice.setText(split[1]);
        animatedPrice.setText(split[2]);
        emotePer.setText(split[3]);
        emotePricePer.setText(split[4]);
        BadgePer.setText(split[5]);
        BadgePricePer.setText(split[6]);
        emorePerSpecial.setText(split[7]);
        emoteSpecialOffer.setText(split[8]);
      /*  JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("settings.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject cost = (JSONObject) obj;
            if(cost.get("EmotesPrice") != "") emotePrice.setText(cost.get("EmotesPrice").toString());
            if(cost.get("BadgePrice") != "") badgePrice.setText(cost.get("BadgePrice").toString());
            if(cost.get("AnimatedEmotesPrice") != "") animatedPrice.setText(cost.get("AnimatedEmotesPrice").toString());
            if(cost.get("EmotePer") != "") emotePer.setText(cost.get("EmotePer").toString());
            if(cost.get("EmotePricePer") != "") emotePricePer.setText(cost.get("EmotePricePer").toString());

            if(cost.get("BadgePer") != "") BadgePer.setText(cost.get("BadgePer").toString());
            if(cost.get("BadgePricePer") != "") BadgePricePer.setText(cost.get("BadgePricePer").toString());

            if(cost.get("EmotePerSpecial") != "") emorePerSpecial.setText(cost.get("EmotePerSpecial").toString());
            if(cost.get("EmoteSpecialOffer") != "") emoteSpecialOffer.setText(cost.get("EmoteSpecialOffer").toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    void writeVaule(KeyEvent event) {
        calculatePrice();

    }
    private void calculatePrice(){
        int sumEmote = sumCost(buysEmotes,costEmotes,costEmotesProm);
        int sumBadge = sumCost(buysBadges,costBadge,costBadgeProm);
        int sumAnimated = sumCost(buysAnimated,costAnimated,costAnimated);
        sumAll = promotionFoEmotes(sumEmote+sumBadge+sumAnimated, getBuysEmotes() + getBuysAnimated());
        buysSum.setText(String.valueOf(sumAll));
    }

    public int sumCost(TextField textField, int cost, int costProm){
        int sum = 0;
        if(textField.getText().isBlank()) return sum;
        int value = Integer.valueOf(textField.getText().trim());
        int prom = textField.getId().equals("buysEmotes") ? emotesPer : 6;
        for(int i = value; i > 0;) {
            if (i % prom == 0 && !textField.getId().equals("buysAnimated"))
            {
                sum += costProm;
                i -= prom;
            } else {
                sum += cost;
                i--;
            }
        }

        return sum;
    }

    public double promotionFoEmotes(int sum, int sizeEmotes){
        if(sizeEmotes >= howMuchToPromotion)
            return sum - (sum * ((double)promo / 100));
        return sum;
    }
}