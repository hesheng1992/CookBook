package com.example.gp62.cookbook.activity

import android.os.Bundle
import android.view.KeyEvent
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.base.BaseAvtivity
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView

/***
 * 自定义二维码扫描
 */
class CapCamerShibieAcitivity : BaseAvtivity() {

    private var capture: CaptureManager? = null
    private var barcodeScannerView: DecoratedBarcodeView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zxing_capture)
        barcodeScannerView = initializeContent()

        capture = CaptureManager(this, barcodeScannerView)
        capture?.initializeFromIntent(intent, savedInstanceState)
        capture?.decode()
    }

    protected fun initializeContent(): DecoratedBarcodeView {
        setContentView(com.google.zxing.client.android.R.layout.zxing_capture)
        return findViewById(com.google.zxing.client.android.R.id.zxing_barcode_scanner) as DecoratedBarcodeView
    }

    override fun onResume() {
        super.onResume()
        capture?.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture?.onSaveInstanceState(outState)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        capture?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return barcodeScannerView?.onKeyDown(keyCode, event)!! || super.onKeyDown(keyCode, event)
    }
}
