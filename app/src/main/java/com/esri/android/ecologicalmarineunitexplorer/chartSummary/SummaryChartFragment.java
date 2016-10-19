package com.esri.android.ecologicalmarineunitexplorer.chartSummary;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.esri.android.ecologicalmarineunitexplorer.R;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.CombinedData;

import java.text.DecimalFormat;
import java.util.List;

/* Copyright 2016 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For additional information, contact:
 * Environmental Systems Research Institute, Inc.
 * Attn: Contracts Dept
 * 380 New York Street
 * Redlands, California, USA 92373
 *
 * email: contracts@esri.com
 *
 */

public class SummaryChartFragment extends Fragment implements SummaryChartContract.View {

  private View mRoot;
  private SummaryChartContract.Presenter mPresenter;

  public static SummaryChartFragment newInstance(){
    return new SummaryChartFragment();
  }


  @Override
  @Nullable
  public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup container,
      final Bundle savedInstance){

    mRoot = layoutInflater.inflate(R.layout.summary_charts, container,false);
    mPresenter.start();
    return mRoot;
  }

  @Override public void showChartData(List<CombinedData> dataList) {
    if (dataList != null){
      int size = dataList.size();
      for (int x=0; x < size; x++){
        CombinedData data = dataList.get(x);
        int viewId = getIdForChartView(x);
        prepareChartView(viewId, data);
      }
    }

  }

  @Override public void setTemperatureText(double temperatureText) {
    TextView textView = (TextView) mRoot.findViewById(R.id.txtTemp);
    textView.setText(Double.valueOf(new DecimalFormat("#.##").format(temperatureText))+"");
  }

  public void setSalinityText(double salinityText) {
    TextView textView = (TextView) mRoot.findViewById(R.id.txtSalinity);
    textView.setText(Double.valueOf(new DecimalFormat("#.##").format(salinityText)) + "");
  }

  @Override public void setOxygenText(double oxygenText) {
    TextView textView = (TextView) mRoot.findViewById(R.id.txtOxygen);
    textView.setText(Double.valueOf(new DecimalFormat("#.##").format(oxygenText)) + "");
  }

  @Override public void setPhosphateText(double phosphateText) {
    TextView textView = (TextView) mRoot.findViewById(R.id.txtPhosphate);
    textView.setText(Double.valueOf(new DecimalFormat("#.##").format(phosphateText)) + "");
  }

  @Override public void setSilicateText(double silicateText) {
    TextView textView = (TextView) mRoot.findViewById(R.id.txtSilicate);
    textView.setText(Double.valueOf(new DecimalFormat("#.##").format(silicateText)) + "");
  }

  @Override public void setNitrateText(double nitrateText) {
    TextView textView = (TextView) mRoot.findViewById(R.id.txtNitrate);
    textView.setText(Double.valueOf(new DecimalFormat("#.##").format(nitrateText)) + "");
  }

  private void prepareChartView(int id, CombinedData data){
    CombinedChart chart = (CombinedChart) mRoot.findViewById(id);
    chart.getPaint(Chart.PAINT_DESCRIPTION).setTextAlign(Paint.Align.CENTER);
    chart.getXAxis().setEnabled(false);
    chart.getAxisRight().setEnabled(false);
    chart.getAxisLeft().setDrawGridLines(false);
    chart.setDescription("");
    chart.setDescriptionTextSize(10f);
    chart.setDrawGridBackground(false);

    chart.setData(data);
    chart.invalidate();

  }

  @Override public void setPresenter(SummaryChartContract.Presenter presenter) {
    mPresenter = presenter;
  }
  private int getIdForChartView(int index){
    int id = 0;
    switch (index){
      case 0:
        id = R.id.chart1;
        break;
      case 1:
        id = R.id.chart2;
        break;
      case 2:
        id = R.id.chart3;
        break;
      case 3:
        id = R.id.chart4;
        break;
      case 4:
        id = R.id.chart5;
        break;
      case 5:
        id = R.id.chart6;
        break;
    }
    return id;
  }
}
