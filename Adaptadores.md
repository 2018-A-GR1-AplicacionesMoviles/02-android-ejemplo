# Adaptadores

```
var notes: ArrayList<NoteInfo> = DataManager.Factory.notes
        val adaterNotes = ArrayAdapter<NoteInfo>(this, android.R.layout.simple_list_item_1, notes)
        list_notes.adapter = adaterNotes
```
