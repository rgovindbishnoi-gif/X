package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Motorcycle Guide", appName)
  }

  @Test
  fun `verify service intervals calculation`() {
    val spec = com.example.data.repository.DemoDataProvider.demoMotorcycles.first()
    val db = com.example.data.db.AppDatabase.getInstance(ApplicationProvider.getApplicationContext())
    val repository = com.example.data.repository.MotorcycleRepository(db)

    val reminders = repository.calculateReminders(
      currentKm = 5800,
      lastServiceKm = 0,
      oilChangedKm = 0,
      chainLubeKm = 5500,
      spec = spec
    )

    // With 5800 km and a 6000 km interval, regular service should be DUE_SOON (<= 500 km)
    val regularService = reminders.find { it.id == "rem_regular_service" }
    org.junit.Assert.assertNotNull(regularService)
    assertEquals(com.example.data.model.ReminderStatus.DUE_SOON, regularService?.status)
  }
}
