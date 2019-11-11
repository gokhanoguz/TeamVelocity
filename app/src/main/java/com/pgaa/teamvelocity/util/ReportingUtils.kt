package com.pgaa.teamvelocity.util

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.pgaa.teamvelocity.data.entity.Sprint
import com.pgaa.teamvelocity.data.model.SprintStats
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

object ReportingUtils {

    const val TAG = "ReportingUtils"

    const val PAGE_WIDTH = 300
    const val PAGE_HEIGHT = 600
    const val PAGE_NUMBER = 1

    fun createFullReportPdf(context: Context, sprintList : List<Sprint>) {
        createFullReportPdf(context, sprintList, SprintUtils.calculateSprintStats(sprintList))
    }

    fun createFullReportPdf(context: Context, sprintList: List<Sprint>, sprintStats: SprintStats) {
        var document = PdfDocument()
        var pageInfo = PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, PAGE_NUMBER).create()

        var page = document.startPage(pageInfo)
        var canvas = page.canvas
        var paint = Paint()
        var y = 20.0f
        var x = 10.0f

        paint.color = Color.RED
        paint.textSize = 20.0f
        canvas.drawText("Sprint Report", x, y, paint)

        paint.color = Color.BLACK
        paint.textSize = 18.0f

        y += 25
        canvas.drawText("Average Ratio: ${sprintStats.avRatio}", x, y, paint)
        y+=20
        canvas.drawText("Average Story Points: ${sprintStats.avStoryPoints}", x, y, paint)
        y+=20
        canvas.drawText("Average Man Day: ${sprintStats.avManDay}", x, y, paint)


        paint.textSize = 15.0f
        paint.color = Color.GRAY
        y+=25
        canvas.drawText("Sprint Story Points & Man Days:", x, y, paint)

        y+=18f
        for(sprint in sprintList) {
            canvas.drawText("${sprint.name} -> ${sprint.storyPoints} | ${sprint.mandDay}", 10.0f, y, paint)
            y += 18f
        }

        document.finishPage(page)
        writeDocumentToPdfFile(context, document, "FullSprintReport_${todaysDate()}.pdf")
        document.close()

    }

    private fun writeDocumentToPdfFile(context: Context, document: PdfDocument, fileName: String) {

        if(!hasExternalStoragePrivateDocuments(context)) {
            Log.e(TAG, "Error: Directory does not exist!")
            Toast.makeText(context, "Directory does not exist!", Toast.LENGTH_SHORT).show()
            return
        }

        val targetFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName)
        try {
            document.writeTo(FileOutputStream(targetFile))
            Toast.makeText(context, "Report is created", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            Log.e(TAG, "Error: $e")
            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hasExternalStoragePrivateDocuments(context: Context): Boolean {
        val path = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        return path != null
    }

    private fun todaysDate(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)
        } else {
            Date().toString()
        }
    }
}