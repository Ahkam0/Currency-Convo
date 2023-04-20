package com.ahkam.currencyconvo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahkam.currencyconvo.data.Conversion
import com.ahkam.currencyconvo.data.ConversionResult
import com.ahkam.currencyconvo.data.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel(private val repository: ConverterRepository) : ViewModel() {

    val rates = "{\n" +
            "    \"success\": true,\n" +
            "    \"timestamp\": 1681973763,\n" +
            "    \"base\": \"USD\",\n" +
            "    \"date\": \"2023-04-20\",\n" +
            "    \"rates\": {\n" +
            "        \"AED\": 3.672398,\n" +
            "        \"AFN\": 85.064049,\n" +
            "        \"ALL\": 102.4506,\n" +
            "        \"AMD\": 386.981363,\n" +
            "        \"ANG\": 1.798616,\n" +
            "        \"AOA\": 506.125993,\n" +
            "        \"ARS\": 217.479903,\n" +
            "        \"AUD\": 1.487595,\n" +
            "        \"AWG\": 1.80235,\n" +
            "        \"AZN\": 1.700507,\n" +
            "        \"BAM\": 1.785027,\n" +
            "        \"BBD\": 2.014968,\n" +
            "        \"BDT\": 105.953435,\n" +
            "        \"BGN\": 1.78588,\n" +
            "        \"BHD\": 0.37699,\n" +
            "        \"BIF\": 2077.944599,\n" +
            "        \"BMD\": 1,\n" +
            "        \"BND\": 1.334094,\n" +
            "        \"BOB\": 6.895324,\n" +
            "        \"BRL\": 5.075998,\n" +
            "        \"BSD\": 0.997946,\n" +
            "        \"BTC\": 3.4517711e-05,\n" +
            "        \"BTN\": 82.182703,\n" +
            "        \"BWP\": 13.174276,\n" +
            "        \"BYN\": 2.518961,\n" +
            "        \"BYR\": 19600,\n" +
            "        \"BZD\": 2.011591,\n" +
            "        \"CAD\": 1.34559,\n" +
            "        \"CDF\": 2047.000037,\n" +
            "        \"CHF\": 0.89544,\n" +
            "        \"CLF\": 0.028799,\n" +
            "        \"CLP\": 794.640322,\n" +
            "        \"CNY\": 6.883599,\n" +
            "        \"COP\": 4534,\n" +
            "        \"CRC\": 533.300476,\n" +
            "        \"CUC\": 1,\n" +
            "        \"CUP\": 26.5,\n" +
            "        \"CVE\": 100.633408,\n" +
            "        \"CZK\": 21.3804,\n" +
            "        \"DJF\": 177.68448,\n" +
            "        \"DKK\": 6.79357,\n" +
            "        \"DOP\": 54.562215,\n" +
            "        \"DZD\": 135.67131,\n" +
            "        \"EGP\": 30.897597,\n" +
            "        \"ERN\": 15,\n" +
            "        \"ETB\": 53.964787,\n" +
            "        \"EUR\": 0.91167,\n" +
            "        \"FJD\": 2.210947,\n" +
            "        \"FKP\": 0.803769,\n" +
            "        \"GBP\": 0.80369,\n" +
            "        \"GEL\": 2.509886,\n" +
            "        \"GGP\": 0.803769,\n" +
            "        \"GHS\": 11.853661,\n" +
            "        \"GIP\": 0.803769,\n" +
            "        \"GMD\": 62.249875,\n" +
            "        \"GNF\": 8577.478415,\n" +
            "        \"GTQ\": 7.784349,\n" +
            "        \"GYD\": 211.06,\n" +
            "        \"HKD\": 7.849395,\n" +
            "        \"HNL\": 24.53953,\n" +
            "        \"HRK\": 6.816133,\n" +
            "        \"HTG\": 155.679277,\n" +
            "        \"HUF\": 345.68398,\n" +
            "        \"IDR\": 14956.1,\n" +
            "        \"ILS\": 3.651549,\n" +
            "        \"IMP\": 0.803769,\n" +
            "        \"INR\": 82.156499,\n" +
            "        \"IQD\": 1316.028458,\n" +
            "        \"IRR\": 42275.000348,\n" +
            "        \"ISK\": 136.279821,\n" +
            "        \"JEP\": 0.803769,\n" +
            "        \"JMD\": 152.379475,\n" +
            "        \"JOD\": 0.709397,\n" +
            "        \"JPY\": 134.470498,\n" +
            "        \"KES\": 135.299227,\n" +
            "        \"KGS\": 87.519847,\n" +
            "        \"KHR\": 4057.865194,\n" +
            "        \"KMF\": 449.296856,\n" +
            "        \"KPW\": 899.995811,\n" +
            "        \"KRW\": 1322.309644,\n" +
            "        \"KWD\": 0.30639,\n" +
            "        \"KYD\": 0.833608,\n" +
            "        \"KZT\": 456.683643,\n" +
            "        \"LAK\": 17092.137088,\n" +
            "        \"LBP\": 14982.521791,\n" +
            "        \"LKR\": 320.362164,\n" +
            "        \"LRD\": 163.050172,\n" +
            "        \"LSL\": 18.190076,\n" +
            "        \"LTL\": 2.95274,\n" +
            "        \"LVL\": 0.60489,\n" +
            "        \"LYD\": 4.757906,\n" +
            "        \"MAD\": 10.177791,\n" +
            "        \"MDL\": 17.94825,\n" +
            "        \"MGA\": 4377.430131,\n" +
            "        \"MKD\": 56.234998,\n" +
            "        \"MMK\": 2095.650983,\n" +
            "        \"MNT\": 3509.346609,\n" +
            "        \"MOP\": 8.068489,\n" +
            "        \"MRO\": 356.999828,\n" +
            "        \"MUR\": 45.099388,\n" +
            "        \"MVR\": 15.349911,\n" +
            "        \"MWK\": 1024.351509,\n" +
            "        \"MXN\": 18.06027,\n" +
            "        \"MYR\": 4.434499,\n" +
            "        \"MZN\": 63.249844,\n" +
            "        \"NAD\": 18.190172,\n" +
            "        \"NGN\": 460.529689,\n" +
            "        \"NIO\": 36.498882,\n" +
            "        \"NOK\": 10.592845,\n" +
            "        \"NPR\": 131.496034,\n" +
            "        \"NZD\": 1.619655,\n" +
            "        \"OMR\": 0.384988,\n" +
            "        \"PAB\": 0.997928,\n" +
            "        \"PEN\": 3.770113,\n" +
            "        \"PGK\": 3.517547,\n" +
            "        \"PHP\": 56.26303,\n" +
            "        \"PKR\": 279.924063,\n" +
            "        \"PLN\": 4.211607,\n" +
            "        \"PYG\": 7110.847442,\n" +
            "        \"QAR\": 3.640503,\n" +
            "        \"RON\": 4.495198,\n" +
            "        \"RSD\": 106.914984,\n" +
            "        \"RUB\": 81.770066,\n" +
            "        \"RWF\": 1107.683321,\n" +
            "        \"SAR\": 3.750296,\n" +
            "        \"SBD\": 8.265733,\n" +
            "        \"SCR\": 12.9174,\n" +
            "        \"SDG\": 599.99997,\n" +
            "        \"SEK\": 10.339265,\n" +
            "        \"SGD\": 1.333265,\n" +
            "        \"SHP\": 1.21675,\n" +
            "        \"SLE\": 22.24033,\n" +
            "        \"SLL\": 19750.00032,\n" +
            "        \"SOS\": 569.501269,\n" +
            "        \"SRD\": 36.811979,\n" +
            "        \"STD\": 20697.981008,\n" +
            "        \"SVC\": 8.731723,\n" +
            "        \"SYP\": 2512.527091,\n" +
            "        \"SZL\": 18.253092,\n" +
            "        \"THB\": 34.369503,\n" +
            "        \"TJS\": 10.897301,\n" +
            "        \"TMT\": 3.5,\n" +
            "        \"TND\": 3.115024,\n" +
            "        \"TOP\": 2.353899,\n" +
            "        \"TRY\": 19.40721,\n" +
            "        \"TTD\": 6.775825,\n" +
            "        \"TWD\": 30.580797,\n" +
            "        \"TZS\": 2347.99974,\n" +
            "        \"UAH\": 36.856666,\n" +
            "        \"UGX\": 3733.822536,\n" +
            "        \"USD\": 1,\n" +
            "        \"UYU\": 38.880142,\n" +
            "        \"UZS\": 11392.220214,\n" +
            "        \"VEF\": 2450712.63246,\n" +
            "        \"VES\": 24.530511,\n" +
            "        \"VND\": 23482.5,\n" +
            "        \"VUV\": 117.798834,\n" +
            "        \"WST\": 2.721201,\n" +
            "        \"XAF\": 598.747741,\n" +
            "        \"XAG\": 0.039607,\n" +
            "        \"XAU\": 0.0005,\n" +
            "        \"XCD\": 2.70255,\n" +
            "        \"XDR\": 0.739673,\n" +
            "        \"XOF\": 598.731347,\n" +
            "        \"XPF\": 109.749938,\n" +
            "        \"YER\": 250.350131,\n" +
            "        \"ZAR\": 18.15342,\n" +
            "        \"ZMK\": 9001.197612,\n" +
            "        \"ZMW\": 17.339479,\n" +
            "        \"ZWL\": 321.999592\n" +
            "    }\n" +
            "}"

    val selectedConversion : MutableState<Conversion?> = mutableStateOf(null)
    val inputText : MutableState<String> = mutableStateOf("")
    val typedValue = mutableStateOf("0.0")

    fun getConversions() = listOf(
        Conversion(1,"Pounds to Kilograms","lbs","kg",0.453592),
        Conversion(2,"Kilograms to Pounds","kg","lbs",2.20462),
        Conversion(3,"Yards to Meters","yd","m",0.9144),
        Conversion(4,"Meters to Yards","m","yd",1.09361),
        Conversion(5,"Miles to Kilometers","mi","km",1.60934),
        Conversion(6,"Kilometers to Miles","km","mi",0.621371)
    )

    val resultList = repository.getSavedResults()

    fun addResult(message1 : String, message2 : String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertResult(ConversionResult(0,message1,message2))
        }
    }

    fun removeResult(item : ConversionResult){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteResult(item)
        }
    }

    fun clearAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllResults()
        }
    }





}