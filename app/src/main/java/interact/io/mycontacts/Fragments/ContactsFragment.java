package interact.io.mycontacts.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interact.io.mycontacts.Activities.Authentification;
import interact.io.mycontacts.Entities.Contact;
import interact.io.mycontacts.R;
import interact.io.mycontacts.Utils.AppController;
import interact.io.mycontacts.Utils.GetContact;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.internal.base.BaseCard;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {
    ArrayList<Card> cards = new ArrayList<Card>();


    private ProgressDialog pDialog;
    private TextView textView;
    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=  inflater.inflate(R.layout.fragment_contacts, container, false);

        textView = (TextView) v.findViewById(R.id.contacts);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        getContacts(getActivity(),textView);
    }

    private void getContacts(final Context context ,final TextView textView) {
        String url = "https://api.mycontacts.io/v2/contacts?&offset=0&limit=20";
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading Contacts...");
        pDialog.show();

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray contactsArray= jsonObject.getJSONArray("data");
                    List<Contact> contacts = new ArrayList<>();
                    for(int i = 0; i<contactsArray.length();i++){
                        JSONObject contactObject = (JSONObject) contactsArray.get(i);
                        Contact c = GetContact.getContact(contactObject);
                        contacts.add(c);

                        //Create ContactCard
                        Card card = new Card(context);
                        CardHeader header = new CardHeader(context);


                        if(c.getProfilePicture()==null){
                            ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
                            int color1 = generator.getRandomColor();
                            String ch=""+c.getFirstName().charAt(0)+c.getLastName().charAt(0);
                            TextDrawable textDrawable = TextDrawable.builder().buildRound(ch, color1);
                            ContactThumbnail thumb = new ContactThumbnail(getActivity(),textDrawable);
                            thumb.setExternalUsage(true);
                            card.addCardThumbnail(thumb);
                        }
                        CardThumbnail thumb = new CardThumbnail(getActivity());
                        thumb.setUrlResource(c.getProfilePicture());
                        card.addCardThumbnail(thumb);
                        header.setTitle(c.getDisplayName());
                        header.setPopupMenu(R.menu.contact_menu, new CardHeader.OnClickCardHeaderPopupMenuListener() {
                            @Override
                            public void onMenuItemClick(BaseCard card, MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.contact_call :{
                                        Toast.makeText(getActivity(), "CALL", Toast.LENGTH_SHORT).show();
                                    }break;
                                    case R.id.contact_sms : {
                                        Toast.makeText(getActivity(), "Send SMS", Toast.LENGTH_SHORT).show();
                                    }break;
                                    case R.id.contact_note:{
                                        Toast.makeText(getActivity(), "Add Note", Toast.LENGTH_SHORT).show();
                                    }break;
                                }
                            }
                        });
                        card.addCardHeader(header);
                        cards.add(card);
                    }
                    CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);
                    CardListView listView = (CardListView) getActivity().findViewById(R.id.contactsList);
                    if (listView!=null){
                        listView.setAdapter(mCardArrayAdapter);
                    }
                    //textView.setText(contacts.toString());
                    Toast.makeText(getActivity(), "OK ", Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "ERREUR GET ARRAY "+e.getMessage(), Toast.LENGTH_LONG).show();
                    pDialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               Toast.makeText(getActivity(), "onErrorResponse "  + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                params.put("authToken", Authentification.user.getAuthToken());
                params.put("Accept","application/json");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map params = new HashMap();
                params.put("authToken", Authentification.user.getAuthToken());
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        // Adding request to request queue
        AppController.getInstance().cancelPendingRequests(AppController.class);
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

   public class ContactThumbnail extends CardThumbnail{
        TextDrawable textDrawable;
       public ContactThumbnail(Context context , TextDrawable txB ) {
           super(context);
           this.textDrawable=txB;
       }

       public ContactThumbnail(Context context, int innerLayout) {
           super(context, innerLayout);
       }

       @Override
       public void setupInnerViewElements(ViewGroup parent, View viewImage) {

           ImageView image= (ImageView)viewImage ;
            image.setImageDrawable(textDrawable);
       }
   }

}
