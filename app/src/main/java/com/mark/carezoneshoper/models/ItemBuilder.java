package com.mark.carezoneshoper.models;

public class ItemBuilder{
        private Item result;
        
        public ItemBuilder(){
            result = new Item();
        }        

        public void setName(String name) {
            this.result.setName(name);
        }

        public void setCreatedAt(String createdAt) {
            this.result.setCreatedAt(createdAt);
        }

        public void setUpdated_at(String updated_at) {
            this.result.setUpdated_at(updated_at);
        }

        public void setCategory(String category) {
            this.result.setCategory(category);
        }

        public void setServerId(long serverId) {
            this.result.setServerId(serverId);
        }

        public Item build(){            
            return result;
        }
    }