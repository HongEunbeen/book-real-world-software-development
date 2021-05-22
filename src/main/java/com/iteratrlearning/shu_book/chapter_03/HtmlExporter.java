package com.iteratrlearning.shu_book.chapter_03;

public class HtmlExporter implements Exporter{
    @Override
    public String export(final SummaryStatistics summaryStatistics){
        String result = "<!doctype html>";
        result += "<html lang = 'en'>";
        result += "<head><title>Bank Transaction Report</title></head>";
        result += "<body>";
        result += "<ul>";
        result += "<li><string>The sum is</string>:" + summaryStatistics.getSum() + "</li>";
        result += "<li><string>The average is</string>:" + summaryStatistics.getAverage() + "</li>";
        result += "<li><string>The max is</string>:" + summaryStatistics.getMax() + "</li>";
        result += "<li><string>The min is</string>:" + summaryStatistics.getMin() + "</li>";
        result += "</ul>";
        result += "</body>";
        result += "</html>";
        return result;
    }
}
