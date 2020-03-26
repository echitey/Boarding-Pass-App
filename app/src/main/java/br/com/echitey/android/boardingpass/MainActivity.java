package br.com.echitey.android.boardingpass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import br.com.echitey.android.boardingpass.databinding.ActivityMainBinding;
import br.com.echitey.android.boardingpass.utilities.FakeDataUtils;

public class MainActivity extends AppCompatActivity {

    // This class is generated based on the layout file
    // Everything should be wrapped in a <layout> tag
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * DataBindUtil.setContentView replaces our normal call of setContent view.
         * DataBindingUtil also created our ActivityMainBinding that we will eventually use to
         * display all of our data.
         */
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        BoardingPassInfo fakeBoardingInfo = FakeDataUtils.generateFakeBoardingPassInfo();
        displayBoardingPassInfo(fakeBoardingInfo);
    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        mainBinding.textViewPassengerName.setText(info.passengerName);
        mainBinding.flightInfo.textViewOriginAirport.setText(info.originCode);
        mainBinding.flightInfo.textViewFlightCode.setText(info.flightCode);
        mainBinding.flightInfo.textViewDestinationAirport.setText(info.destCode);

        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault());
        String boardingTime = formatter.format(info.boardingTime);
        String departureTime = formatter.format(info.departureTime);
        String arrivalTime = formatter.format(info.arrivalTime);

        mainBinding.textViewBoardingTime.setText(boardingTime);
        mainBinding.textViewDepartureTime.setText(departureTime);
        mainBinding.textViewArrivalTime.setText(arrivalTime);

        long totalMinutesUntilBoarding = info.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minutesLessHoursUntilBoarding =
                totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,
                hoursUntilBoarding,
                minutesLessHoursUntilBoarding);

        mainBinding.textViewBoardingInCountdown.setText(hoursAndMinutesUntilBoarding);
        mainBinding.boardingInfo.textViewTerminal.setText(info.departureTerminal);
        mainBinding.boardingInfo.textViewGate.setText(info.departureGate);
        mainBinding.boardingInfo.textViewSeat.setText(info.seatNumber);

    }
}
